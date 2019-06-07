package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import biz.UserBiz;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserBiz ub;
	
	@RequestMapping("/find")
	public String findAll(HttpServletRequest request){
		//request.setAttribute("ALLUSER", ub.findAllUser());
		return "index";
	}
	
	@RequestMapping(value="/finduser",produces="application/json;charset=utf-8")
	@ResponseBody
	public String findAllUser(@RequestParam(defaultValue="1",value="page") int nums){
		// ½«List-->jsonstr
		String str = JSONArray.toJSONString(ub.findAllUser((nums-1)*10));
		str = "{\"total\":59,\"rows\":" + str+"}";
		System.out.println(str);
		return str;
	}
	
}
