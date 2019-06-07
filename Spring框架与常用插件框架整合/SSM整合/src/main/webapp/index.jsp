<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<c:if test="${empty ALLUSER }">
		<c:redirect url="user/find.spring" />
	</c:if>

	<c:forEach var="ui" items="${ALLUSER }">
		${ui.userName }<br/>
	</c:forEach>
</body>
</html>
