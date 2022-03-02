<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<title>부서 관리</title>
<script>

	/* 비지니스 로직 성공 alert 메시지 처리 */
	const message = '${ requestScope.message }';
	if(message != null && message !== '') {
		alert(message);
	}
</script>
<style>
#output_header {
	position: absolute;
	left: 300px;
	top: 100px;
	font-size: 1.6rem;
	width: 1500px;
	height: 813px;
}

.box {
	width: 600px;
	height: 570px;
	/* border-style: solid; */
	border-color: #888686;
	background-color: #F3F3F3;
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
	height: 40px;
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

.box2_body {
	font-size: 1.2rem;
	position: absolute;
	left: 40px;
	top: 60px;
	margin-left: 20px;
	margin-right: 20px;
}

textarea {
	border-radius: 5px;
	font-size: 1.2rem;
}

.button {
			@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
			-webkit-appearance: none;
            -moz-appearance: none ;
            appearance: none ;

            margin: 0;
            padding: 0.3rem 1.3rem ;

            font-family: "Noto Sans KR ", sans-serif ;
            font-size: 1rem ;
            font-weight: 400;
            text-align: center ;
            text-decoration: none ;

            display: inline-block ;
            width: auto ;

            border: none ;
            border-radius: 4px ;

            cursor: pointer ;
            transition: 0.5s ;

            box-shadow: 3px 3px 3px gray ;

            margin: 10px ;
            margin-bottom: 20px ;
        
	
}

#dept-add {
	background-color: #343A40;
	color: white;
}

#dept-delete {
	background-color: #D16B6B;
	color: white;
}

#searchButton {
	background-color: #343A40;
	color: white;
	border-color: #888686;
	margin: 0px;
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
	transform: translateY(-7px);
}

#searchBox {
	border-color: #B4ADAD;
	border-radius: 10px;
	border-style: 1px;
	width: 350px;
	margin-bottom: 10px;
	font-size: 1.2rem;
}

#box2_content1 {
	margin-right: 65px;
}

#box2_content2 {
	margin-right: 77px;
}

#box2_content3 {
	margin-right: 48px;
}

#box2_content4 {
	margin-right: 60px;
}

.person-info {
	margin-top: 10px;
	margin-left: 35px;
	margin-right: 35px;
}

/* 모달 */
.modal-content {
  width: 635px;
  height: 272px;
  padding: 30px;
}
.my-modal-input label {
  width: 60px;
}
.my-modal-input input, select {
  width: 365px;
}
.my-modal-body {
  margin-left: 0px;
}
.my-modal-footer {
  text-align: right;
}
.my-modal-footer button {
  color: #000;
  background: none;
  padding: 5px 25px;
}
.my-modal-footer button:first-child {
  margin-right: 5px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
		
		<!-- 부서 추가 모달 -->
		<%-- <div class="modal fade" id="writeModal" data-bs-backdrop="static"
			tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
				<div class="modal-content" style="top: 172px">
					<form action="${ pageContext.servletContext.contextPath }/company/dept/regist" method="POST">
						<div class="my-modal-header mb-3">
							<h3>부서 생성</h3>
						</div>
						<div class="my-modal-body">
							<div class="my-modal-input mb-3">
								<label class="me-2" for="name-write">부서명</label>
								<input type="text" id="name-write" name="name">
							</div>
							<div class="my-modal-input mb-4">
								<label class="me-2" for="code-write">부서코드</label>
								<input type="text" id="code-write" name="code">
							</div>
						</div>
						<div class="my-modal-footer">
							<button type="submit" class="btn btn-secondary"
								data-bs-toggle="modal">등록</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">취소</button>
						</div>
					</form>
				</div>
			</div>
		</div> --%>
		
		<!-- 팀 추가 모달 -->
		<div class="modal fade" id="writeModal" data-bs-backdrop="static"
			tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
				<div class="modal-content" style="top: 172px">
					<form action="${ pageContext.servletContext.contextPath }/company/team/regist" method="POST">
						<div class="my-modal-header mb-3">
							<h3>팀 추가</h3>
						</div>
						<div class="my-modal-body">
							<div class="my-modal-input mb-3">
								<label class="me-2" for="dept-select">상위부서</label>
								<select id="dept-select" name="dept">
									<c:forEach var="dept" items="${ requestScope.deptList }">
										<option><c:out value="${ dept.name }" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="my-modal-input mb-3">
								<label class="me-2" for="name-write">팀명</label>
								<input type="text" id="name-write" name="name">
							</div>
							<div class="my-modal-input mb-4">
								<label class="me-2" for="code-write">팀코드</label>
								<input type="text" id="code-write" name="code">
							</div>
						</div>
						<div class="my-modal-footer">
							<button type="submit" class="btn btn-secondary"
								data-bs-toggle="modal">등록</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">취소</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<main>
			<div id="output_header">
				<i style='font-size: 24px' class='fas'>&#xf4fe;</i>부서관리
				<hr>
				<div class="box" id="outputbox1">
					<div id="box1header">
						부서 관리 <br>
						<hr style="margin: 0px;">
						<div id="box1_body">
							<button  rel="float" class="button float" id="dept-add">
								<i style='font-size: 16px' class='fas' style='color: white;'>&#xf550;</i>&nbsp;부서
								추가
							</button>
							<button  rel="float" class="button float" id="dept-add" data-bs-toggle="modal" data-bs-target="#writeModal">
								<i style='font-size: 16px' class='fas' style='color: white;'>&#xf550;</i>&nbsp;팀
								추가
							</button>
							<button  rel="float" class="button float" id="dept-add">
								<i style='font-size: 16px' class='far' style='color: white;'>&#xf044;</i>&nbsp;수정
							</button>
							<button  rel="float" class="button float" id="dept-delete">
								<i style='font-size: 16px' class='fas' style='color: white;'>&#xf2ed;</i>&nbsp;삭제
							</button>
							
							<!-- <br> <label type="button" class="folder_toggle"
								data-toggle="collapse" data-target="#demo"><i
								style='font-size: 24px' class='fas'>&#xf07b;</i>어쩌고</label>
							<div id="demo" class="collapse">
								<p>
									저쩌고잉<br> 저꾸쩌<br> 드를어<br>
								</p>
							</div>
							<br> <label type="button" class="folder_toggle"
								data-toggle="collapse" data-target="#demo2"><i
								style='font-size: 24px' class='fas'>&#xf07b;</i>저쩌고</label>
							<div id="demo2" class="collapse">
								<p>
									저쩌고잉<br> 저꾸쩌<br> 드를어<br>
								</p>
							</div>
							<br> <label type="button" class="folder_toggle"
								data-toggle="collapse" data-target="#demo3"><i
								style='font-size: 24px' class='fas'>&#xf07b;</i>흠냐리스트</label>

							<div id="demo3" class="collapse">
								<p>
									저쩌고잉<br> 저꾸쩌<br> 드를어<br>
								</p>
							</div>
							<br> <label type="button" class="folder_toggle"
								data-toggle="collapse" data-target="#demo4"><i
								style='font-size: 24px' class='fas'>&#xf07b;</i>드르렁</label>

							<div id="demo4" class="collapse">
								<p>
									저쩌고잉<br> 저꾸쩌<br> 드를어<br>
								</p>
							</div> -->
							
							<c:forEach var="dept" items="${ requestScope.deptList }">
								<ul id="listArea" style="list-style: none">
									<li><i style='font-size: 24px' class='fas'>&#xf07b;</i><c:out value="${ dept.name }" /></li>
								</ul>
							</c:forEach>
						</div>
					</div>


				</div>

				<div class="box" id="outputbox2">
					<div id="box2_border_top">
						<p id="box2_header">&nbsp;&nbsp; 부서 정보</p>
						<div class="box2_body" id="box2_sq1">
							<!--  value="<c:out value="${ requestScope.selectCriteria.searchValue }"/> -->
							<label class="box2_content" id="box2_content1">부서명</label> <input
								id="searchBox" type="search" id="searchValue"
								name="searchDeptName"> <br> <label
								class="box2_content" id="box2_content2">팀 명</label> <input
								id="searchBox" type="search" id="searchValue"
								name="searchDeptName"> <br> <label
								class="box2_content" id="box2_content3">부서코드</label> <input
								id="searchBox" type="search" id="searchValue"
								name="searchDeptName"> <br> <label
								class="box2_content" id="box2_content4">팀 코드</label> <input
								id="searchBox" type="search" id="searchValue"
								name="searchDeptName"> <br>
							<div align="center">
								<button  rel="float" class="button float" id="searchButton" type="submit">
									<i style="font-size: 16px" class="fa">&#xf002;</i>&nbsp;검색
								</button>
							</div>
							<hr style="color: #343A40;" margin="0px">
							<label id="deptPerson">부서 인원</label>
							<hr style="margin: 0px;">
							<div align="center">
								<label class="person-info">이름</label> <label class="person-info">부서</label>
								<label class="person-info">직급</label> <label class="person-info">전화번호</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>