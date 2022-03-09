<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>


		<h1>meetingDetail page</h1>
		
		<label>작성자 : <c:out value="${ requestScope.meeting.member.memberName }" /></label>
		<label>글 제목 : </label><input type="text">
		<label>글 내용 : </label><textarea cols="10" rows="20" readonly></textarea>
	<button onclick="location.href='${ pageContext.servletContext.contextPath }/meeting/list'">뒤로가기</button>
	<button onclick="location.href='${ pageContext.servletContext.contextPath }/meeting/delete/${requestScope.meeting.no }'">뒤로가기</button>
		
		
		
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>