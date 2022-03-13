<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>errorPage</title>
</head>
<body>
	<h1 align="center">${ requestScope.exception.message }</h1>
	<h1 align="center">${ requestScope.message }</h1>
</body>
</html>