<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">    
<html xmlns='http://www.w3.org/1999/xhtml'>
    <head>
        <meta http-equiv='Content-Type', content='text/html; charset=utf-8'>
        <title>单点登录客户端1</title>
    <script type="text/javascript">
  	function logout(){
  		var allSessionId=document.getElementById("allSessionId").value;
  		
  		window.location.href ="http://localhost:8088/SSOServer/user/logout?allSessionId="+allSessionId+"&redirectUrl=http://localhost:8088/SSOClient1";
  	}
    </script>
    </head>
	<body style="padding:60px;padding-bottom:40px;">
    
    <h2 style="color:red">客户端1登入后才能显示的界面</h2>
    
    <h3>userName is:  <p style="color:red">${userName}</p></h3>
   	<input type="hidden" id="allSessionId" value=${allSessionId}>
   	<input type="button" onclick="logout()" value="退出登录">
  
     
	</body>
</html>
