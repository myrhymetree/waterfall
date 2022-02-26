<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
        integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">      
<title>직급 관리</title>
<style>

.main-content {
	position: absolute;
	left: 230px;
	top: 10px;
	width: 1700px;
	height: 900px;
}

.jobadminheader {
	position: absolute;
	left: 100px;
	top: 100px;
	width: 1500px;
	height: 700px;
}

.table-area {
	border: solid;
	border-style: 1px;
	width: 90%;
	height: 60%;
	border-radius: 1%;
	
}

#table-header {
	font-size: 1.6rem;
}

#table-content {
	font-size: 1.4rem;
}

.button {@import
	url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");-webkit-appearance: none
	;
            -moz-appearance: none ;
            appearance: none ;

            margin: 0; padding: 0.5rem 1rem ;

            font-family: "Noto Sans KR ", sans-serif ;
            font-size: 1rem ;
            font-weight: 400; text-align: center ;
            text-decoration: none ;

            display: inline-block ;
            width: auto ;

            border: none ;
            border-radius: 4px ;

            cursor: pointer ;
            transition: 0.5s ;

            box-shadow: 3px 3px 3px gray ;

            margin: 3px ;
        
	
}

#modifyButton {
	background-color: rgb(197, 197, 197);
}

#deleteButton {
	background-color: rgb(177, 209, 221);
}
#addButton{
	background-color: rgb(173, 206, 173);
}

.float {
	display: inline-block;
	transition-duration: 0.3s;
	transition-property: transform;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	transform: translateZ(0);
	box-shadow: 0 0 1px rgba(0, 0, 0, 0);
}

.float:hover {
	transform: translateY(-5px);
}
td{
	vertical-align: middle !important;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<main>
			<!-- 메뉴바 내 콘텐트 영역 -->
			<div class="main-content">
				<!-- 직급관리 헤더 영역 -->
				<div class="jobadminheader">
					<label style="font-size: 1.4rem; margin-right: 1200px;">
						<i style='font-size: 24px' class='far'>&#xf2bb;</i>직급관리
					</label>
					<button class="float button" id="addButton">추가</button>
					<hr>
					<!-- 직급 추가 모달 -->
					
					<!-- 직급검색창-->
					<br> 
					<!-- 직급명 목록 조회 -->
					<table  class="table table-bordered">
						<thead>
							<tr>
								<th>RANK</th>
								<th>직급명</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="job" items="${ requestScope.jobList }">
								<tr>
									<td> <c:out value="${ job.rank }" />
									</td>
									<td> <c:out value="${ job.name }" />
									</td>
									<td><button class="float button" id="modifyButton">수정</button>
										<button class="float button" id="deleteButton">삭제</button></td>
								</tr>
							</c:forEach>
							<%-- <tr>
								<td>2 <c:out value="${ job.rank }" />
								</td>
								<td>이사 <c:out value="${ job.name }" />
								</td>
								<td><button class="float button" id="modifyButton">수정</button>
									<button class="float button" id="deleteButton">삭제</button></td>
							</tr> --%>
						</tbody>

					</table>


				</div>
				
			</div>
		</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>