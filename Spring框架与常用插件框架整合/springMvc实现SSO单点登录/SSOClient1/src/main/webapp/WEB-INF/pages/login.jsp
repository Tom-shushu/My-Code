<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客户端1登录页面</title>
</head>
<body style="text-algin:center">
	
	<h1 style="color:red">客户端1的登录界面</h1>
	<form action=${requestScope.url} method="post">
		<input type="text" name="userName" >账号
		</br>
		<input type="password" name="userPassword" >密码
		</br>
		<input type="hidden" name="redirectUrl" value="http://localhost:8088/SSOClient1">
		<button type="submit" >登录</button>
	</form>
	
</body>
</html>