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


		<h1>meetingDetail page</h1>
		
		<label>글 번호 : <c:out value="${ requestScope.meeting.no }" /></label>
		<label>글 제목 : <c:out value="${ requestScope.meeting.title }" /></label>
		<label>글 내용 : <c:out value="${ requestScope.meeting.content }" /></label>
		<label>작성자 : <c:out value="${ requestScope.meeting.member.memberName }" /></label>
		
		
		
		
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		
</body>
</html>