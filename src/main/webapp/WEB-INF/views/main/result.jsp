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

<table align="center">
	<tr>
		<th>번호</th>
		<th>이름</th>
	</tr>
	<c:forEach var="test" items="${ requestScope.testList }">
		<tr>
			<td><c:out value="${ test.no }"/></td>
			<td><c:out value="${ test.name }"/></td>
		</tr>
	
	</c:forEach>
</table>
</body>
</html>