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
		<div class="row" align="right">
			<div class="col">
				<button onclick="location.href='${ pageContext.servletContext.contextPath }/meeting/remove/${ requestScope.meeting.no }'">삭제</button>
				<button onclick="location.href='${ pageContext.servletContext.contextPath }/meeting/modify/${ requestScope.meeting.no }'">수정</button>
				
			</div>
		</div>
		<label>글 번호 : <c:out value="${ requestScope.meeting.no }" /></label>
		<label>작성자 : <c:out value="${ requestScope.meeting.member.memberName }" /></label>
		<label>글 제목 : <c:out value="${ requestScope.meeting.title }" /></label>
		<label>글 내용 : </label><textarea cols="10" rows="20" readonly><c:out value="${ requestScope.meeting.content }"/></textarea>
		<button onclick="location.href='${ pageContext.servletContext.contextPath }/meeting/list'">뒤로가기</button>
		
		
		
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		
</body>
</html>