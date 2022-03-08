<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>


	<c:if test="${ sessionScope.loginMember.role eq 1 }">
		<jsp:include page="/WEB-INF/views/main/adminMain.jsp"/>
	</c:if>
	
	<c:if test="${ sessionScope.loginMember.role eq 2 }">
		<jsp:include page="/WEB-INF/views/main/memberMain.jsp"/>
	</c:if>
	
	<c:if test="${ sessionScope.loginMember.role eq 3 }">
		<jsp:include page="/WEB-INF/views/main/clientMain.jsp"/>
	</c:if>

</body>
</html>