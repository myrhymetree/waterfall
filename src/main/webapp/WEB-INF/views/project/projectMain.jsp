<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>
	<h1 align="center">pm : ${ projectAutority.pmNo }</h1>
	<h1 align="center">projectNo : ${ projectAutority.projectNo }</h1>
	<h1 align="center">${ sessionScope.loginMember.no }</h1>
	<h1 align="center">${ sessionScope.loginMember.role }</h1>
	

requestScope.loginMember and (sessionScope.loginMember.no eq sessionScope.projectAutority.pmNo or sessionScope.loginMember.role eq '1')
	
	
	
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>