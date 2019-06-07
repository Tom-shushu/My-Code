package test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


import bean.UserInfo;
import biz.UserBiz;

public class Test {

	public static void main(String[] args) {
		ApplicationContext bean = new FileSystemXmlApplicationContext("src/main/resources/spring.xml");
		UserBiz ub = bean.getBean("userBizImpl",UserBiz.class);
		List<UserInfo> list = ub.findAllUser();
		System.out.println(list.size());
	}
}
