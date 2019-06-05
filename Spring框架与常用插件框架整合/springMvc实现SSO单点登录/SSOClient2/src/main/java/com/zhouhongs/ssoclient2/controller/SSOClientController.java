package com.zhouhongs.ssoclient2.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.zhouhongs.ssoclient2.util.SessionUtil;


/**  
 * .--,       .--,
 *( (  \.---./  ) )
 * '.__/o   o\__.'   _______________________________________________________
 *    {=  ^  =}  <===代码无BUG!                                              |
 *     >  -  <       | Code is far away from bug with the animal protecting|
 *    /       \      ```````````````````````````````````````````````````````
 *   //       \\
 *  //|   .   |\\
 *  "'\       /'"_.-~^`'-.
 *     \  _  /--'         `
 *   ___)( )(___       
 *  (((__) (__)))    
 *
 * <p>Title: SSOClientController</p>  
 * <p>Description: </p>  
 * @author 周红  
 * @date 2019年6月5日  
 *客户端部分，本想只用一个session存储局部会话，收到服务端的退出请求后直接调用request.getSession().removeAttribute("token")清空局部会话;
 *结果发现，服务端利用httpClient通知客户端的时候是新建立的一个会话，此时的session和我们局部建立的session并不是同一个。
 *解决办法:
 *自己维护一个session管理类，利用map将局部会话的session对象和id存储起来。收到请求后再销毁该session
 */  
@Controller
public class SSOClientController {

	//拦截所有获取资源请求
	@RequestMapping("")
	public String ssoClient(HttpServletRequest request,ModelMap map){
		
		//判断请求的链接中是否有token参数
		String token=request.getParameter("token");
		String url=request.getParameter("url");

		if(token!=null){
			//如果有表示是认证服务器返回的
			String allSessionId=request.getParameter("allSessionId");
			return "redirect:http://localhost:8088/SSOClient2/checkToken?token="+token+"&allSessionId="+allSessionId;
		}else if(url!=null){
			
			return "redirect:http://localhost:8088/SSOClient2/login?url="+url;
		}else{
			//其他请求，继续判断是否创建了和用户之间的局部会话
			JSONObject j=(JSONObject) request.getSession().getAttribute("token");
			if(j!=null){
				System.out.println("已经登录,存在局部会话1:"+j);
				System.out.println("本次局部会话的localSessionId:"+request.getSession().getId());
				map.addAttribute("userName", j.getString("userName"));
				map.addAttribute("allSessionId", j.getString("allSessionId"));
				return "index";
			}else{
				//未登录
				
				return "redirect:http://localhost:8088/SSOServer?clientUrl=http://localhost:8088/SSOClient2";
			}
		}	
	}
	
	//客户端接收token并且进行验证
	@RequestMapping(value="/checkToken")
	public String checkToken(HttpServletRequest request,ModelMap map){
		
		String token=request.getParameter("token");
		String allSessionId=request.getParameter("allSessionId");
		
		//利用httpClient进行验证
		String basePath = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod("http://localhost:8088/SSOServer/tokenCheck");
		postMethod.addParameter("token", token);
		postMethod.addParameter("allSessionId", allSessionId);
		postMethod.addParameter("clientUrl",basePath);
		
		try {
			httpClient.executeMethod(postMethod);
			String resultJson = postMethod.getResponseBodyAsString();
			
			postMethod.releaseConnection();
			//用httpClient得到的json数据默认被转义了两次变成了"{\\"header\\":\\"认证成功!\\",\\"userName\\":\\"admin\\",\\"erroeCode\\":0}"
			//需要数据还原 \\" 变成  " 同时去掉前后的双引号 
			
			resultJson=resultJson.replaceAll("\\\\\"", "\"");
			resultJson=resultJson.substring(1, resultJson.length()-1);
			JSONObject j=JSONObject.parseObject(resultJson);
			j.put("allSessionId", allSessionId);
			int errorCode=j.getIntValue("erroeCode");
			if(errorCode==0){
				//创建客户端和用户的局部会话
				request.getSession().setAttribute("token", j);
				String localSessionId=request.getSession().getId();
				HttpSession localSession=request.getSession();
				System.out.println("创建局部会话,localSessionId是:"+request.getSession().getId());
				map.addAttribute("userName", j.getString("userName"));
				map.addAttribute("allSessionId", j.getString("allSessionId"));
				//存储局部会话
				
				SessionUtil.setSession(localSessionId, localSession);
				//存储对应关系
				SessionUtil.setLink(allSessionId, localSessionId);
				
			}else{
				
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
	
	//客户端登录
	@RequestMapping(value="/login")
	public ModelAndView login(HttpServletRequest request){
		
		String url=request.getParameter("url");
		ModelAndView model=new ModelAndView();
		model.setViewName("login");
		model.addObject("url", url);
		return model;
	}
	
	//退出
	@RequestMapping(value="/logout")
	public void logout(String allSessionId){
		
		System.out.println("客户端2收到退出请求");
		String localSessionId=SessionUtil.getLocalSessionId(allSessionId);
		
		HttpSession localSession=SessionUtil.getSession(localSessionId);
		
		localSession.removeAttribute("token");
		
		//localSession.invalidate();
		
	}
}