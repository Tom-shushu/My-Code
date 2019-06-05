package com.zhouhongs.ssoserver.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouhongs.ssoserver.bean.User;
import com.zhouhongs.ssoserver.dao.UserDao;
import com.zhouhongs.ssoserver.util.TokenUtil;
import com.zhouhongs.ssoserver.util.UrlUtil;

/**
 * 
 * @author Administrator
 *
 */
@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UserDao baseDao;
	@RequestMapping("/getName")
	public ModelAndView getName(){
		ModelAndView model=new ModelAndView("index");
		
		String userName=baseDao.getName();
		model.addObject("userName",userName);
		return model;
	}
	
	//登录验证
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response){
		ModelAndView model=new ModelAndView();
		String userName=request.getParameter("userName");
		String userPassword=request.getParameter("userPassword");
		String redirectUrl=request.getParameter("redirectUrl");
		User user=baseDao.login(userName,userPassword);
		if(user!=null){
			//设置状态(通过session判断该浏览器与认证中心的全局会话是否已经建立)，生成令牌
			request.getSession().setAttribute("isLogin", userName);
			String token = UUID.randomUUID().toString();
			
			//存储
			TokenUtil.put(token, userName);
			/*设置cookie到浏览器
			Cookie cookie=new Cookie("sso", userName);
			cookie.setMaxAge(60);
			response.addCookie(cookie);
			*/
			//将token发送给客户端,附带本次全局会话的sessionId
			String allSessionId=request.getSession().getId();
			System.out.println("全局会话allSessionId:"+allSessionId);
			return "redirect:"+redirectUrl+"?token="+token+"&allSessionId="+allSessionId;	
		}
		return "redirect:http://localhost:8088/SSOServer/redirectUrl?msg=loginError";
	}
	
	@RequestMapping(value="/redirectUrl",method=RequestMethod.POST)
	public ModelAndView redirectUrl(HttpServletRequest request){
		ModelAndView model=new ModelAndView();
		String msg=request.getParameter("msg");
		if(msg.equals("loginError")){
			msg="账号密码错误";
			model.setViewName("error");
			model.addObject("msg",msg);
		}
		return model;
	}
	
	//登出
	@RequestMapping(value="/logout")
	public String logOut(String allSessionId,String redirectUrl,HttpServletRequest request){
		String url=UrlUtil.get(allSessionId);
		UrlUtil.remove(allSessionId);
		//删除全局会话
		request.getSession().removeAttribute("isLogin");
		
		//通知各个客户端删除局部会话
		String [] urls=url.split(",");
		//使用httpClient通知客户端的时候发现是新建立了一个服务器与客户端的会话，导致sessionId和客户建立的局部会话id不相同，无法做到删除局部会话
		HttpClient httpClient=new HttpClient();
		PostMethod postMethod=new PostMethod();
		
		for (String u : urls) {
			
			postMethod.setPath(u+"/logout");
			postMethod.addParameter("allSessionId", allSessionId);
			
			try {
				httpClient.executeMethod(postMethod);
				postMethod.releaseConnection();
			
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("报错1:路径是: redirect:"+redirectUrl);
		return "redirect:"+redirectUrl;
	}
	
}
