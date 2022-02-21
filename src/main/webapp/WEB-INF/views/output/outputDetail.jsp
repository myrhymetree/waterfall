<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
	crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${ pageContext.servletContext.contextPath }/css/styles.css" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">	
<style>
#output_header {
	position: absolute;
	left: 300px;
	top: 50px;
	font-size: 1.4rem;
	width: 90%;
	height: 800px;
}

.box {
	width: 600px;
	height: 570px;
	border-style: solid;
	border-color: #888686;
}

#outputbox1 {
	position: absolute;
	left: 100px;
	top: 100px;
	border-width: 1px;
}

#outputbox2 {
	position: absolute;
	left: 800px;
	top: 100px;
	border-width: 1px;
}

#box2_border_top {
	width: 600px;
	height: 50px;
	background-color: #343A40;
}

#box1header {
	padding: 40px;
	font-size: 1.4rem;
}

#headerunderline {
	position: absolute;
	left: 40px;
	top: 45px;
}

#box1_body {
	font-size: 1.2rem;
}

#box2_header {
	font-size: 1.4rem;
	color: white;
	padding: 5px;
}

.box2_sql_body {
	background-color: #F6F6F6;
	font-size: 1rem;
	padding: 3px;
	box-shadow: 2px 2px 2px 2px gray;
}

textarea {
	border-radius: 5px;
	font-size: 1.2rem;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp" />
	<div id="layoutSidenav_content">
		<main>
			<div id="output_header">
				<i style='font-size: 24px' class='fas'>&#xf07c;</i>산출물
				<hr>

				<div class="box" id="outputbox1">
					<div id="box1header"> 산출물 관리 <br>
						<hr>
						<div id="box1_body">
							<%-- 상위업무 forEach --%>
							<c:forEach var="parentTask" items="${ requestScope.parentTaskList }" varStatus="status" >
								<input type="button" class="folder_toggle" data-toggle="collapse" data-target="#demo1" >
								<i style='font-size: 14px'class='fas'>&#xf07b;</i>
								<c:out value="${ parentTask.taskCategory.categoryName }"/><br>
							</c:forEach>
							
							<%--하위 업무 forEach --%>
							<c:forEach var="childList" items="${ requestScope.parentTaskList[ status.index ].childList }" varStatus="status">
							<div id="demo1" class="collapse" >
								<div id="childTasks${ status.index }">
									<input type="button" value="${ childList.taskCategory.categoryName }">		
								</div>
							</div>	
							</c:forEach>
						</div>
					</div>
				</div>

				<div class="box" id="outputbox2">
					<div id="box2_border_top">
						<p id="box2_header">&nbsp;&nbsp;v1 요구사항</p>
						<div class="box2_body" id="box2_sq1">
							<p type="button" id="box1_body"
								style="position: absolute; left: 500px; top: 60px;"
								data-toggle="modal" data-target="#myModal">
								<i style='font-size: 24px' class='far'>&#xf2ed;</i>삭제
							</p>
							<span class="box2_sql_body"
								style="position: absolute; left: 45px; top: 70px;">프로젝트이름</span>
							<p id="box1_body"
								style="position: absolute; left: 180px; top: 70px;">도레미파솔라시도시라</p>
							<span class="box2_sql_body"
								style="position: absolute; left: 45px; top: 120px;">상위업무2</span>
							<span class="box2_sql_body"
								style="position: absolute; left: 45px; top: 170px;">등록인</span> <span
								class="box2_sql_body"
								style="position: absolute; left: 180px; top: 170px;">c:out
								value="${ requestScope.out.member }</span> <span class="box2_sql_body"
								style="position: absolute; left: 45px; top: 220px;">등록일</span> <span
								class="box2_sql_body"
								style="position: absolute; left: 180px; top: 220px;">c:out
								value="${ requestScope.out.date }</span> <span class="box2_sql_body"
								style="position: absolute; left: 45px; top: 270px;">첨부파일</span>
							<span class="box2_sql_body"
								style="position: absolute; left: 180px; top: 270px;">c:out
								value="${ requestScope.out.body }</span> <span class="box2_sql_body"
								style="position: absolute; left: 45px; top: 320px;">내용</span>
							<textarea cols="35" rows="5"
								style="position: absolute; left: 180px; top: 320px;"
								style="font-size: 10px;" ></textarea>
							<span class="box2_sql_body" type="button"
								style="position: absolute; left: 420px; top: 510px;">확인</span> <span
								class="box2_sql_body" type="button"
								style="position: absolute; left: 490px; top: 510px;">취소</span>
						</div>
					</div>
				</div>
			</div>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>

	
</body>
</html>