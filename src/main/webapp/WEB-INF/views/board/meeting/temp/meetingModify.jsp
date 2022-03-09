<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/c24bc5c6f2.js" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>


		<h1>meetingDetail page</h1>
		<form action="${ pageContext.servletContext.contextPath }/meeting/modify/${ requestScope.meeting.no }" method="post">
		
			<label>글 번호 : </label><input type="text" name="no" value="${ requestScope.meeting.no }" readonly>
			<label>작성자 : <c:out value="${ requestScope.meeting.member.memberName }" /></label>
			<label for="title">글제목 : </label><input type="text" id="title" name="title" value="${ requestScope.meeting.title }">
			<label for="meeting-context">글 내용 : </label><textarea id="meeting-context" name="content" cols="10" rows="20" ><c:out value="${ requestScope.meeting.content }"/></textarea>
			<button type="submit">저장</button>
			<input type="button" onclick="location.href='${ pageContext.servletContext.contextPath }/meeting/detail/${ requestScope.meeting.no }'">
		</form>
		
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		
</body>
</html>