<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
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
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">		
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" data-auto-replace-svg="nest"></script>

<!-- 간트차트 -->	
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/frappe-gantt/0.3.0/frappe-gantt.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/frappe-gantt/0.3.0/frappe-gantt.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/frappe-gantt/0.3.0/frappe-gantt.min.css">
<script type="text/javascript"
	src="https://unpkg.com/frappe-gantt@0.6.0/src/index.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/frappe-gantt/0.3.0/frappe-gantt.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">

<style>
#box1header {
	margin: 30px;
	font-size: 18px;
	font-weight: 600;
}

.box {
	width: 1600px !important;
	height: 800px;
	display: flex;
	margin: 30px;
}

.box2 {
	width: 1400px;
	height: 800px;
	margin-left: 50px;
	font-size: 18px;
}

#box1_body {
	width: 180px;
}

.upDown {
	border: 0.2px solid gray;
	overflow: visible;
	width: 0.3px;
	height: 800px;
	display: inline-box;
}

body {
	font-family: 'Noto Sans KR', sans-serif;
	background: #ccc;
}

.container {
	width: 80%;
	margin: 0 auto;
}
/* custom class */
.gantt .bar-milestone .bar {
	fill: tomato;
}
/* 업무 생성 */
a {
	color: black;
	text-decoration: none;
}

#layoutSidenav_content .task-regist {
	position: relative;
}

#layoutSidenav_content .task-regist h2 {
	height: 50px;
	line-height: 1.5;
	padding-left: 60px;
}

#layoutSidenav_content .task-regist h2 span {
	font-size: 1rem;
	vertical-align: bottom;
}

#layoutSidenav_content .task-regist h2 span img {
	width: 20px;
	vertical-align: bottom;
}

#layoutSidenav_content .task-regist hr {
	width: 95%;
	margin: 10px auto;
}

#layoutSidenav_content .task-regist .sect1 {
	position: fixed;
	width: 305px;
	height: 75%;
	border-right: 1px solid black;
}

#layoutSidenav_content .task-regist .sect1 p {
	padding-left: 42px;
	line-height: 1;
	/* font-size: 1rem; */
}
/* 레이어 팝업 버튼 */
#layoutSidenav_content .task-regist .sect1 p .layerPopup {
	vertical-align: middle;
	cursor: pointer;
}

#layoutSidenav_content .task-regist .sect2 {
	width: 75%;
	margin-left: 305px;
}
/* 레이어 창 */
#layoutSidenav_content .task-regist .layer {
	display: none;
	width: 777px;
	height: 580px;
	background: #fff;
	border: 1px solid black;
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	z-index: 2500;
}

.layer {
	display: none;
	width: 850px;
	height: 750px;
	background: #fff;
	border: 1px solid black;
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	z-index: 2500;
	/* font-size: 1rem; */
	cursor: pointer;
	border-radius:10px;
	overflow : auto;
}
.add{
	display: none;
	width: 770px;
	height: 530px !important;
	background: #fff;
	border: 1px solid black;
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	z-index: 2500;
	/* font-size: 1rem; */
	cursor: pointer;
	border-radius:10px;
	overflow : auto;
}

#layoutSidenav_content .task-regist .layer p {
	/* font-size: 1rem; */
	padding-left: 30px;
	padding-right: 30px;
}

#layoutSidenav_content .task-regist .layer p .trash {
	cursor: pointer;
}

#layoutSidenav_content .task-regist .layer p button {
	border: 0;
	padding: 3px 8px;
	border-radius: 5px;
	background-color: #f8f8f8;
	box-shadow: 1px 4px 3px #bbb;
}

#layoutSidenav_content .task-regist .layer p label {
	padding: 3px 8px;
	border-radius: 5px;
	background-color: #f8f8f8;
	box-shadow: 1px 4px 3px #bbb;
}

#layoutSidenav_content .task-regist .layer p select {
	vertical-align: middle;
	width: 150px;
	background-color: #fff;
	box-shadow: 1px 4px 3px #ddd inset;
}

#layoutSidenav_content .task-regist .layer p #start-date,
	#layoutSidenav_content .task-regist .layer p #end-date {
	vertical-align: middle;
}

#layoutSidenav_content .task-regist .layer p .milestone {
	vertical-align: middle;
}

.layer-bg {
	display: none;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.6);
	position: absolute;
	left: 0;
	top: 0;
	z-index: 2000;
}

/* 업무 수정 */
ul {
	list-style: none;
}

#layoutSidenav_content .task-regist .sect1 .task-list {
	/* font-size: 1rem; */
	margin-top: 50px;
	padding-left: 42px;
}

#layoutSidenav_content .task-regist .sect1 .task-list li {
	height: 40px;
	vertical-align: middle;
}

#layoutSidenav_content .task-regist .sect1 .task-list li span {
	line-height: 41px;
}

#layoutSidenav_content .task-regist .sect1 .task-list li.layerPopup {
	cursor: pointer;
}

.layerPopup {
	cursor: pointer;
}

#layoutSidenav_content .task-regist .sect1 .task-list li.subtask {
	text-indent: 15px;
}

.close {
	font-size: 1rem;
}


.header{
	background-color: #3E3C3C;
	color : white;
	padding:10px;
	border-top-left-radius:10px;
	border-top-right-radius:10px;
}
.button {
			@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
			-webkit-appearance: none;
            -moz-appearance: none ;
            appearance: none ;

            margin: 0;
            padding-left: 0.7rem;
            padding-right: 0.7rem;
            padding-top : 0.4rem;
            padding-bottom:0.4rem;

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
#footerBtn{
	padding-left: 500px;
}
#readModal{

}

#parentTaskDetail{
	width : 40%;
	float : left;
	
}

#childTaskDetail{
	width : 50%;
	float : right;
	
	
}
.task{
	border:solid;
	padding:5px;
	border-color:gray;
	border-radius:5px;
	border-width : 1px;
	
}
#outputModal.modal-overlay {
	width: 100%;
	height: 100%;
	position: absolute;
	left: 0;
	top: 0;
	display: none;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
	backdrop-filter: blur(0.5px);
	-webkit-backdrop-filter: blur(0.5px);
	border-radius: 10px;
	border: 1px solid rgba(255, 255, 255, 0.18);
}
#outputModal .modal-window {
	background: rgba( 69, 139, 197, 0.70 );
	box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.37 );
	backdrop-filter: blur( 13.5px );
	-webkit-backdrop-filter: blur( 13.5px );
	border-radius: 10px;
	border: 1px solid rgba( 255, 255, 255, 0.18 );
	width: 400px;
	height: 500px;
	position: relative;
	top: -100px;
	padding: 10px;
        }
#outputModal .title {
	padding-left: 10px;
	display: inline;
	text-shadow: 1px 1px 2px gray;
	color: white;
            
}
#outputModal .title h2 {
	display: inline;
}
#outputModal .close-area {
	display: inline;
	float: right;
	padding-right: 10px;
	cursor: pointer;
	text-shadow: 1px 1px 2px gray;
	color: white;
}
        
#outputModal .content-area {
	margin-top: 20px;
	padding: 0px 10px;
	text-shadow: 1px 1px 2px gray;
	color: white;
}
#parentTaskModifyBtn, #childTaskModifyBtn{
	background-color: #9B91BF;
	color: white;
	margin : 0px;
}
#parentTaskDeleteBtn, #childTaskDeleteBtn{
	background-color : #D16B6B;
	color: white;
	margin : 3px;

}



</style>
<script>
	/* 비지니스 로직 성공 alert 메시지 처리 */
	const message = '${ requestScope.message }';
	if(message != null && message !== '') {
		alert(message);
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp" />
	<div class="taskList">
		<div id="box1header">
			<i style='font-size:24px' class='far'>&#xf328;</i>&nbsp;&nbsp;업무 관리 <br>
		</div>
		<hr>
		<div class="box">
			<div id="box1_body">
				<div class="sect1 mt-3">
					<c:if test="${ sessionScope.loginMember.role eq 1 || sessionScope.loginMember.no == sessionScope.projectAutority.pmNo }">
						<p>
							<span class="layerPopup"><img src="${ pageContext.servletContext.contextPath }/resources/assets/img/generate.png">업무생성</span>
						</p>
					</c:if>
				</div>
				<%-- 상위업무 forEach --%>

				<c:forEach var="parentTask" items="${ requestScope.parentTaskList }"
					varStatus="status">
					<label  type="button" class="folder_toggle"
						data-toggle="collapse" data-target="#demo${ status.index }"><i
						style='font-size: 16px;' class='fas'>&#xf07b;</i>&nbsp;<c:out
							value="${ parentTask.taskCategory.categoryName }" /></label>
					<br>
					<%--하위 업무 forEach --%>
					<c:forEach var="childList"
						items="${ requestScope.parentTaskList[ status.index ].childList }"
						varStatus="st">

						<div id="demo${ status.index }" class="collapse">
							<div id="childTasks${ st.index }">
								<div id="childTask">
									<label class=childName type="button"><i
										style='font-size: 24px' class='fas'>&#xf0da;</i>
									<c:out value="${ childList.taskCategory.categoryName }" /></label> <input
										id="childDTO${ st.index }" type="hidden"
										value="${ childList.taskNo }">
								</div>
							</div>
						</div>
					</c:forEach>
					<%-- <c:out value="${ parentTask.taskCategory.categoryName }"/><br> --%>
				</c:forEach>
			</div>
			<div class="upDown"></div>
			<div class="box2">
				<div class="container">
					<h2>Gantt Chart</h2>
					<div class="gantt-target"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- 업무 생성 레이어 -->
	<form action="${ pageContext.servletContext.contextPath }/task/regist" method="post">
	<div>
		<div class="layer add" id="addModal">
		<div class="header">업무 생성</div>
			<div style="margin-left:30px;">
				<p class="text-end mt-3">
				</p>
				<p>
					<label>업무선택</label>
					<select class="task-register-code" name="taskCode" required>
					<option value="" selected disabled >선택</option>
					<c:forEach var="taskList" items="${requestScope.taskCategoryList }" varStatus="status" >
						<option value="${taskList.categoryCode }" ><c:out value="${ taskList.categoryName }"/></option>
					</c:forEach>	
					</select> <span class="trash"><i class="far fa-trash-alt ms-2"></i></span>
				</p>
				<p>
					<label>종속관계</label>
					<select class="relation ms-2 me-5" name="parentTaskCode" required>
					<option value="" selected disabled >선택</option>
					<c:forEach var="taskCode" items="${requestScope.allTaskCode.parentCategory }" varStatus="status">
						<option value="${taskCode.parentCategoryCode }" ><c:out value="${taskCode.parentCategoryName }"/></option>
					</c:forEach>
						<option value= "NULL">미지정</option>
					</select>
					<label>담당자</label><i class="far fa-user ms-2"></i>
					<select class="in-charge ms-0" name="taskMember" required>
						<option value="" selected disabled>선택</option>
						<c:forEach var="projectMember" items="${requestScope.projectMemberList }" varStatus="status">
						<option value="${projectMember.memberNo }" ><c:out value="${projectMember.memberName }"/></option>
						</c:forEach>
					</select>
				</p>
				<p>
					<label for="start-date">시작일</label><i
						class="far fa-calendar-alt ms-2"></i>
						<input type="date" id="start-date" name="startDate" required>
				</p>
				<p>
					<label for="end-date">종료일</label><i class="far fa-calendar-alt ms-2"></i>
					<input type="date" id="end-date" name="deadline" required>
				</p>
				<p>
					<label>중요도</label>
					<select class="importance ms-2" name="importance" required>
						<option value="" selected disabled>선택</option>
						<option value="낮음">낮음</option>
						<option value="보통">보통</option>
						<option value="긴급">긴급</option>
					</select>
				</p>
				<p>
					<label>진행상태</label> <select class="status ms-2" name="progressStatus" required>
						<option value="" selected disabled>선택</option>
						<option value="진행전">진행전</option>
						<option value="진행중">진행중</option>
						<option value="테스트중">테스트중</option>
						<option value="진행완료">진행완료</option>
						<option value="보류">보류</option>
					</select>
				</p>
				<p>
					<label>진행률</label>
					<input class="rate ms-2" type="number" name="progressRatio" value="0" min="0" max="100" >%
				</p>
				
				<p>
					<label>마일스톤</label>
					<input class="milestone ms-2" type="checkbox" name="typeNo" value="2">
					<input type="hidden" name="typeNo" value="1">
				</p>
				</div>
					<div id="footerBtn">
						<button class="addTask button float" type="submit" style="padding:4px; " rel="float">업무 생성</button>
						<input type="button" id="close" class="button float" style="padding:4px; margin-right:30px;" rel="float" value="업무 나가기">
					</div>
			
		</div>
	</div>
	</form>
	<!-- 업무 생성 끝 -->
	
	<!-- 업무 수정 모달 -->
	<form action="${ pageContext.servletContext.contextPath }/task/update" method="post">
	<div>
		<div class="layer add" id="modifyModal">
		<div class="header">업무 수정</div>
			<div style="margin-left:30px;">
				<p class="text-end mt-3">
				</p>
				<p>
					<label>업무선택</label>
					<select id="modifyCategoryName" class="task-register-code" name="taskCode" required>
					<option value="" disabled >선택</option>
					<c:forEach var="taskList" items="${requestScope.taskCategoryList }" varStatus="status" >
						<option id="modifyCategoryName" value="${taskList.categoryCode }" ><c:out value="${ taskList.categoryName }"/></option>
					</c:forEach>	
					</select> <span class="trash"><i class="far fa-trash-alt ms-2"></i></span>
				</p>
				<p>
					<label>종속관계</label>
					<select id="modifyParentTaskCode" class="relation ms-2 me-5" name="parentTaskCode" required>
					<option value="" disabled >선택</option>
					<c:forEach var="taskCode" items="${requestScope.allTaskCode.parentCategory }" varStatus="status">
						<option value="${taskCode.parentCategoryCode }" ><c:out value="${taskCode.parentCategoryName }"/></option>
					</c:forEach>
						<option id="selectedNull" value= "NULL">미지정</option>
					</select>
					<label>담당자</label><i class="far fa-user ms-2"></i>
					<select id="modifyMember" class="in-charge ms-0" name="taskMember" required>
						<option value="" disabled>선택</option>
						<c:forEach var="projectMember" items="${requestScope.projectMemberList }" varStatus="status">
						<option id="modifyMember" value="${projectMember.memberNo }" ><c:out value="${projectMember.memberName }"/></option>
						</c:forEach>
					</select>
				</p>
				<p>
					<label for="start-date">시작일</label><i
						class="far fa-calendar-alt ms-2"></i>
						<input id="modifyStartDate" type="date" id="start-date" name="startDate" required>
				</p>
				<p>
					<label for="end-date">종료일</label><i class="far fa-calendar-alt ms-2"></i>
					<input id="modifyDeadline" type="date" id="end-date" name="deadline" required>
				</p>
				<p>
					<label>중요도</label>
					<select id="modifyImportance" class="importance ms-2" name="importance" required>
						<option value="" disabled>선택</option>
						<option value="낮음">낮음</option>
						<option value="보통">보통</option>
						<option value="긴급">긴급</option>
					</select>
				</p>
				<p>
					<label>진행상태</label>
					<select id="modifyStatus" class="status ms-2" name="progressStatus" required>
						<option value="" selected disabled>선택</option>
						<option value="진행전">진행전</option>
						<option value="진행중">진행중</option>
						<option value="테스트중">테스트중</option>
						<option value="진행완료">진행완료</option>
						<option value="보류">보류</option>
					</select>
				</p>
				<p>
					<label>진행률</label>
					<input id="modifyRatio" class="rate ms-2" type="number" name="progressRatio" value="0" min="0" max="100" >%
				</p>
				
				<p>
					<label>마일스톤</label>
					<input class="milestone ms-2" type="checkbox" name="typeNo" value="2">
					<input type="hidden" name="typeNo" value="1">
				</p>
				</div>
					<div id="footerBtn">
						<button class="addTask button float" type="submit" style="padding:4px; " rel="float">업무 수정</button>
						<input type="hidden" id="modifyTaskNo" name="taskNo">
						<input type="button" id="closeModify" class="button float" style="padding:4px; margin-right:30px;" rel="float" value="업무 나가기">
					</div>
			
		</div>
	</div>
	</form>
	<!-- 업무 수정 모달 끝 -->
		
	 <%-- 업무 조회 모달 --%>
	<div>
		<div class="layer" id="readModal">
		<div class="header">업무 조회</div>
			<div style="margin-left:30px;">
				<p class="text-end mt-3">
				</p>
				<div class="detail" id="parentTaskDetail">
				<label class="task">상위업무</label>
				<c:if test="${ sessionScope.loginMember.role eq 1 || sessionScope.loginMember.no == sessionScope.projectAutority.pmNo }">
				<button class="button float" id="parentTaskModifyBtn">수정</button>
				<button class="button float" id="parentTaskDeleteBtn" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
				</c:if>
					<p>
						<label>업무명</label>
						<input id="parentTaskName" type="text" name="parentTaskName"/>
						<input type="hidden" name="parentTaskCode" id="parentTaskCode" />
					</p>
					<p>
						<label>담당자</label>
						<input id="parentTaskManager" type="text" name="parentTaskManager"/>
						<input id="parentTaskManagerNo" type="hidden" name="parentTaskManagerNo">
					</p>	
					<p>
						<label>시작일</label>
						<input type="date" id="parent-start-date" name="parentStartDate">
					</p>	
					<p>
						<label>마감일</label>
						<input type="date" id="parent-end-date" name="parentDeadline">
					</p>
					<p>
						<label>중요도</label>
						<input type="text" id="parentImportance" name="parentImportance">
					</p>
					<p>
						<label>진행률</label>
						<input id="parentProgress" class="rate ms-2" type="number" name="parentProgress" value="0" min="0" max="100" >%
					</p>
					<p>
						<label>진행상태</label>
						<input id="parentStatus" type="text" name="parentStatus" >
					</p>
					<p>
						<label>마일스톤</label>
						<input class="milestone ms-2" type="checkbox" name="typeNo" value="2">
						<input type="hidden" name="typeNo" value="1">
					</p>
					<p>
						<label>이슈 등록</label>
							<label id="addIssue">
							<button class="button float">등록</button>
							<input type="hidden" name="parentTaskNo">
							</label>
							
					</p>
					
					<p>
						<label>산출물 등록</label>
							<c:if test="${ sessionScope.loginMember.role eq 1 || sessionScope.loginMember.no == sessionScope.projectAutority.pmNo }">
								<button id="addOutput1" class="button float">등록</button>
								<input id="outputParentTaskNo" type="hidden" name="parentTaskNo">
							</c:if>
							<c:if test="${ sessionScope.loginMember.role eq 2 && sessionScope.loginMember.no != sessionScope.projectAutority.pmNo }">
								<input id="outputFileName1" type="file" name="outputFile" readonly>
							</c:if>
					</p>
				</div>
				<div class="detail"id="childTaskDetail">
				<label class="task">하위업무</label>
				<c:if test="${ sessionScope.loginMember.role eq 1 || sessionScope.loginMember.no == sessionScope.projectAutority.pmNo }">
				<button class="button float" id="childTaskModifyBtn">수정</button>
				<button class="button float" id="childTaskDeleteBtn" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
				</c:if>
					<p>
						<label>업무명</label>
						<input id="childTaskName" type="text" name="childTaskName"/>
						<input id="childTaskCode" type="hidden" name="childTaskCode"/>
					</p>
					
					<p>
						<label>담당자</label>
						<input id="childTaskManager" type="text" name="childTaskManager"/>
						<input id="childTaskManagerNo" type="hidden" name="childTaskManagerNo">
					</p>
					
					<p>
						<label>시작일</label>
						<input type="date" id="child-start-date" name="childStartDate">
					</p>
					
					<p>
						<label>마감일</label>
						<input type="date" id="child-end-date" name="childDeadline">
					</p>
					
					<p>
						<label>중요도</label>
						<input type="text" id="childImportance" name="childImportance">
					</p>
					
					<p>
						<label>진행률</label>
						<input id="childProgress" class="rate ms-2" type="number" name="childProgress" value="0" min="0" max="100" >%
					</p>
					
					<p>
						<label>진행상태</label>
						<input id="childStatus" type="text" name="childStatus" >
					</p>
					
					<p>
						<label>마일스톤</label>
						<input class="milestone ms-2" type="checkbox" name="typeNo" value="2">
						<input type="hidden" name="typeNo" value="1">
					</p>
					
					<p>
						<label>이슈 등록</label>
							<label id="addIssue">
							<button class="button float">등록</button>
							<input type="hidden" name="childTaskNo">
							</label>
					</p>
					
					<p>
						<label>산출물 등록</label>
							<c:if test="${ sessionScope.loginMember.role eq 1 || sessionScope.loginMember.no == sessionScope.projectAutority.pmNo }">
								<button id="addOutput2" class="button float">등록</button>
								<input id="outputChildTaskNo" type="hidden" name="childTaskNo">
							</c:if>
							<c:if test="${ sessionScope.loginMember.role eq 2 && sessionScope.loginMember.no != sessionScope.projectAutority.pmNo }">
								<input id=outputFileName2 type="file" name="outputFile" readonly>
							</c:if>
					</p>
					</div>
						<button id="close" class="button float" style="float: none; padding:4px; margin-right:30px; margin-left: 600px;"  rel="float">업무 나가기</button>
				
			</div>
		</div>
	</div>
	<%--업무 조회 모달 끝 --%>
	
	<!-- 삭제 확인 Modal "modal-dialog-scrollable" 클래스에 추가하면 모달 길어지면 스크롤 생깁니다. -->
              <div class="modal fade modal-dialog-scrollable" id="deleteModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                    <div class="modal-content" style="top: 200px">
                        <div style="background-color: #212529;">
                            <br>
                        </div>
                        <div class="modal-header">
                            <span class="modal-title" id="exampleModalLabel"><strong>업무 삭제</strong></span>
                        </div> 
                    <!-- 모달의 바디 부분 내용물 채우면 저절로 크기는 늘어남  -->
                            <div class="modal-body">
                                <div class="mb-3">
                                    <!--label for랑 input id랑 일치시키면 라벨에 타이틀을 적을경우 라벨눌르면 인풋박스안 텍스트로 포커스를 맞춘다  -->
                                    <!-- placeholder는 인풋박스안에 적을 내용 기술해두 되고 빼두되고 편한대로 -->
                                  <label for="clieck-point" class="col-form-label" style="margin-left: 35%; margin-top:5%;">삭제하시겠습니까?</label>
                                  <input type="hidden" id="deleteParentTaskNo">
                                  <input type="hidden" id="deleteChildTaskNo">
                                </div>
                            </div>
                            <!-- 모달의 바디 끝  -->
                            <div class="modal-footer">
                                <button type="button" id="delete" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
                                <button id="cancelDelete" style="margin-right: 36%;" type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                            </div>
                    </div>
                </div>
            </div>
	
	
	
	<%-- 산출물 등록 모달 --%>
	<form action="${ pageContext.servletContext.contextPath }/output/regist" method="post" encType="multipart/form-data">
	<div id="outputModal" class="modal-overlay">
		<div class="modal-window">
			<div class="title">
				<h4>산출물 등록</h4>
			</div>
			<div class="content-area">
				<textarea name="content" rows="8" cols="45"></textarea>
			</div>
			<p>파일 업로드</p>
			<input id="addOutput" type="file" name="outputFile" required>
			<div class="close-area">
				<button type="submit" id="submitOutput" class="button float">등록</button>
				<input type="button" id="closeOutput" class="button float" value="돌아가기">
				<input type="hidden" id="outputModalTaskNo" name="taskNo">
			</div>
		</div>
	</div>
	</form>
	
	
	<script>
	<%--Modal drag 이벤트 --%>
	/* $(function(){
		$('#addModal').draggable({ handle: ".header" });

	}); */
	
	$("#parentTaskDeleteBtn").click(function(){
		
		if($("#readModal").css("display")=="none"){
			$("#readModal").css("display", "blcok");
		} else{
			$("#readModal").css("display", "none");
		}
		$("#delete").click(function(){
			const deleteParentTaskNo = $("#outputParentTaskNo").val();
			console.log(deleteParentTaskNo);
			
			/*상위 업무 삭제를 클릭했을 때*/
			location.href="${ pageContext.servletContext.contextPath }/task/delete?taskNo=" + deleteParentTaskNo ;
		});
	});
	$("#childTaskDeleteBtn").click(function(){
		
		if($("#readModal").css("display")=="none"){
			$("#readModal").css("display", "blcok");
		} else{
			$("#readModal").css("display", "none");
		}
		$("#delete").click(function(){
			const deleteChildTaskNo = $("#outputChildTaskNo").val();
			console.log(deleteChildTaskNo);
			
			/*하위 업무 삭제를 클릭했을 때*/
			location.href="${ pageContext.servletContext.contextPath }/task/delete?taskNo=" + deleteChildTaskNo ;
		});
		
	});
	$("#cancelDelete").click(function(){
		if($("#readModal").css("display")=="none"){
			$("#readModal").css("display", "blcok");
		} 
	});
	
	<%-- 상위 업무 수정 이벤트 시작 --%>
	/* 업무조회 모달에서 수정버튼을 클릭했을 경우 */
	$("#parentTaskModifyBtn").click(function(){
		/* 수정모달에 보여질 정보들을 넣어준다. */
		const modifyTaskCode= $("#parentTaskCode").val();
		const modifyMemberName = $("#parentTaskManager").val();
		const modifyMemberNo = $("#parentTaskManagerNo").val();
		const modifyStartDate = $("#parent-start-date").val();
		const modifyDeadline = $("#parent-end-date").val();
		const modifyImportance = $("#parentImportance").val();
		const modifyRatio = $("#parentProgress").val();
		const modifyStatus = $("#parentStatus").val();
		const modifyTaskNo = $("#outputParentTaskNo").val();
		console.log(modifyTaskCode);
		console.log(modifyMemberName);
		console.log(modifyStartDate);
		console.log(modifyDeadline);
		console.log(modifyImportance);
		console.log(modifyRatio);
		console.log(modifyTaskNo);
		$("#modifyCategoryName").val(modifyTaskCode).prop("selected", true);		//value가 선택한 taskCode와 동일한 것을 선택
		$("#selectedNull").val('미지정').prop("selected", true);
		$("#modifyMember").val(modifyMemberNo).prop("selected", true);
		$("#modifyStartDate").val(modifyStartDate).prop("selected", true);
		$("#modifyDeadline").val(modifyDeadline).prop("selected", true);
		$("#modifyImportance").val(modifyImportance).prop("selected", true);
		$("#modifyStatus").val(modifyStatus).prop("selected", true);
		$("#modifyRatio").val(modifyRatio);
		$("#modifyTaskNo").val(modifyTaskNo);
		
		
		if($("#modifyModal").css("display")=="none"){
			$("#modifyModal").css("display", "block");
			$("#readModal").css("display", "none");
		}	
	});
	
	/* 하위업무 수정을 클릭하였을 때 */
	$("#childTaskModifyBtn").click(function(){
		/* 수정모달에 보여질 정보들을 넣어준다. */
		const modifyTaskCode= $("#childTaskCode").val();
		const modifyMemberName = $("#childTaskManager").val();
		const modifyMemberNo = $("#childTaskManagerNo").val();
		const modifyStartDate = $("#child-start-date").val();
		const modifyDeadline = $("#child-end-date").val();
		const modifyImportance = $("#childImportance").val();
		const modifyRatio = $("#childProgress").val();
		const modifyStatus = $("#childStatus").val();
		const modifyParentTaskCode = $("#parentTaskCode").val();
		const modifyTaskNo = $("#outputChildTaskNo").val();
		
		console.log(modifyTaskCode);
		console.log(modifyMemberName);
		console.log(modifyStartDate);
		console.log(modifyDeadline);
		console.log(modifyImportance);
		console.log(modifyRatio);
		console.log(modifyParentTaskCode);
		console.log(modifyTaskNo);
		$("#modifyCategoryName").val(modifyTaskCode).prop("selected", true);		//value가 선택한 taskCode와 동일한 것을 선택
		$("#modifyParentTaskCode").val(modifyParentTaskCode).prop("selected", true);
		$("#modifyMember").val(modifyMemberNo).prop("selected", true);
		$("#modifyStartDate").val(modifyStartDate).prop("selected", true);
		$("#modifyDeadline").val(modifyDeadline).prop("selected", true);
		$("#modifyImportance").val(modifyImportance).prop("selected", true);
		$("#modifyStatus").val(modifyStatus).prop("selected", true);
		$("#modifyRatio").val(modifyRatio);
		$("#modifyTaskNo").val(modifyTaskNo);
		
		if($("#modifyModal").css("display")=="none"){
			$("#modifyModal").css("display", "block");
			$("#readModal").css("display", "none");
		}	
	});
	
	$("#closeModify").click(function(){
		$("#modifyModal").css("display", "none");
		$("#readModal").css("display", "block");
		
	});


	<%-- 상위업무 산출물 --%>
	$("#addOutput1").click(function(){
		if($("#outputModal").css("display")=="none"){
			$("#outputModal").css("display", "flex");
			$("#readModal").css("display", "none");
			
		}
		
		const taskNo = Number($("#outputParentTaskNo").val());
		$("#outputModalTaskNo").val(Number(taskNo));
		console.log(Number(taskNo));
		console.log($("#outputModalTaskNo").val());
	});
	
	<%-- 하위업무 산출물 --%>
	$("#addOutput2").click(function(){
		if($("#outputModal").css("display")=="none"){
			$("#outputModal").css("display", "flex");
			$("#readModal").css("display", "none");
			
		}
		
		const taskNo = $("#outputChildTaskNo").val();
		$("#outputModalTaskNo").val(Number(taskNo));
		console.log(Number(taskNo));
		console.log($("#outputModalTaskNo").val());
	});
	
	$("#closeOutput").click(function(){
		$("#readModal").css("display", "block");
		$("#outputModal").css("display", "none");
	});
	
	<%-- hover event 추가 --%>
		$(".folder_toggle").hover(function() {
			$(this).css({
				"border" : "solid",
				"border-color" : "gray",
				"border-radius" : "5px",
				"border-width" : "1px"
			});

		}, function() {
			$(this).css({
				"border-color" : "white"
			});
		});

		$(".childName").hover(function() {
			$(this).css({
				"border" : "solid",
				"border-color" : "gray",
				"border-radius" : "5px",
				"border-width" : "1px"
			});

		}, function() {
			$(this).css({
				"border-color" : "white"
			});
		});
		
		$(".addTask").hover(function(){
			$(this).css({
				"border" : "solid",
				"border-color" : "gray",
			    "border-radius" : "5px",
			    "border-width" : "1px"});
			},function(){
				$(this).css({"border-color" : "white"});
		});
		
		$(".close").hover(function(){
			$(this).css({
				"border" : "solid",
				"border-color" : "gray",
			    "border-radius" : "5px",
			    "border-width" : "1px"});
			}, function(){
				$(this).css({"border-color" : "white"});
		});
	<%-- hover 이벤트 끝 --%>
	

		// 모달 이벤트
		$(".layerPopup").click(function() {
			$("#addModal").css("display", "block");
			$(".layer-bg").css("display", "block");
			
			
		});
		$(".layer #close").click(function() {
			$("#addModal").css("display", "none");
			$(".layer-bg").css("display", "none");
		});
		
		$(".layer #close").click(function() {
			$("#readModal").css("display", "none");
			$(".layer-bg").css("display", "none");
		});
		
		$(".layer #close").click(function() {
			$("#modifyModal").css("display", "none");
			$(".layer-bg").css("display", "none");
		});
		
		
		/* 상세조회 이벤트 */
		const $labels = document.querySelectorAll("#childTask label");
		console.log($labels);
		const $taskNo = document.querySelectorAll("#childTask input");
		console.log($taskNo);
		
		for(let i = 0; i < $labels.length; i++){
			const taskNo = $($taskNo[i]).val();
			console.log(taskNo);
			$($labels[i]).click(function() {
				$.ajax({
					url : "/waterfall/task/detail",
					type : "get",
					data : {"taskNo" : taskNo},
					success : function(data, textStatus, xhr) {
						const taskDetail = JSON.parse(data.taskDetail);
						console.log(taskDetail.taskNo);
						console.log(taskDetail.parentTask.taskNo);
						
						//상위업무
						$("input[name=parentTaskName]").val(taskDetail.parentTask.taskCategory.categoryName);
						
						$("input[name=parentTaskManager]").val(taskDetail.parentTask.managerName);
						
						$("input[name=parentTaskManagerNo]").val(taskDetail.parentTask.managerNo);
						
						$("input[name=parentStartDate]").val(taskDetail.parentTask.startDate);
						
						$("input[name=parentDeadline]").val(taskDetail.parentTask.deadline);
						
						$("input[name=parentImportance]").val(taskDetail.parentTask.importance);
						
						$("input[name=parentProgress]").val(taskDetail.parentTask.progression);
						
						$("input[name=parentTaskNo]").val(taskDetail.parentTask.taskNo);
						
						$("input[name=parentTaskCode]").val(taskDetail.parentTask.taskCategory.categoryCode);
						
						$("input[name=parentStatus]").val(taskDetail.parentTask.progressStatus);
						
						//하위 업무
						$("input[name=childTaskName]").val(taskDetail.taskCategory.categoryName);
						
						$("input[name=childTaskCode]").val(taskDetail.taskCategory.categoryCode);
						
						$("input[name=childTaskManager]").val(taskDetail.managerName);
						
						$("input[name=childTaskManagerNo]").val(taskDetail.managerNo);
						
						$("input[name=childStartDate]").val(taskDetail.startDate);
						
						$("input[name=childDeadline]").val(taskDetail.deadline);
						
						$("input[name=childImportance]").val(taskDetail.importance);
						
						$("input[name=childProgress]").val(taskDetail.progression);
						
						$("input[name=childTaskNo]").val(taskDetail.taskNo);
						
						$("input[name=childStatus]").val(taskDetail.progressStatus);
						
						
						
						$("#readModal").css("display", "block");
						$(".layer-bg").css("display", "block");
						
					}, error:function(data){
						console.log(data);
					}
					
				});
			});
		}
		/* 상세조회 이벤트 끝 */
		
	<%--Gantt Chart data--%>

		var parentTaskArray = new Array();
		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		parentTaskArray[${status.index}] = "${parentTaskList[status.index]}";
		console.log(parentTaskArray[${status.index}]);
		</c:forEach>
		
		var startDateArray = new Array();
		
		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		startDateArray[${status.index}] = "${parentTaskList[status.index].startDate}";
		console.log(startDateArray[${status.index}]);
		</c:forEach>
		
		console.log(startDateArray);
		
		var deadlineArray = new Array();
		
		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		deadlineArray[${status.index}] = "${parentTaskList[status.index].deadline}";
		</c:forEach>
		console.log(deadlineArray)
		
		var parentTaskName = new Array();
		
		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		parentTaskName[${status.index}]= "${parentTaskList[status.index].taskCategory.categoryName}";
		</c:forEach>
		console.log(parentTaskName);
		
		var parentTaskNo = new Array();
		
		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		parentTaskNo[${status.index}] = "${parentTaskList[status.index].taskNo}";
		</c:forEach>
		console.log(parentTaskNo);
		
		var parentProgress = new Array();
		
		<c:forEach items="${parentTaskList}" var="task" varStatus ="status">
		parentProgress[${status.index}] = "${parentTaskList[status.index].progression}";
		</c:forEach>
		console.log(parentProgress);
		// 상위업무 정보 끝
		
		//하위업무 정보 시작
		console.log("========================하위업무=================================")
		var childTaskList = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childTaskList[${status.index}] = "${childTaskList[status.index]}";
		</c:forEach>
		console.log(childTaskList.length);
		
		var childStartDateArr = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childStartDateArr[${status.index}] = "${childTaskList[status.index].startDate}";
		</c:forEach>
		console.log(childStartDateArr);
		
		var childDeadlineArr = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childDeadlineArr[${status.index}] = "${childTaskList[status.index].deadline}";
		</c:forEach>
		console.log(childDeadlineArr);
		
		var childTaskName = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childTaskName[${status.index}] = "${childTaskList[status.index].taskCategory.categoryName}";
		</c:forEach>
		console.log(childTaskName);
		
		var childTaskNoArr = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childTaskNoArr[${status.index}] = "${childTaskList[status.index].taskNo}";
		</c:forEach>
		console.log(childTaskNoArr);
		
		var childTaskProgressionArr = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childTaskProgressionArr[${status.index}] = "${childTaskList[status.index].progression}";
		</c:forEach>
		console.log(childTaskProgressionArr);
		
		var childDependenciesArr = new Array();
		<c:forEach items="${childTaskList}" var="childTask" varStatus ="status">
		childDependenciesArr[${status.index}] = "${childTaskList[status.index].parentTaskNo}";
		</c:forEach>
		console.log(childDependenciesArr);
		
		
		
		var taskArray = new Array();
		console.log(taskArray.length)
		
		for(let i = 0; i < parentTaskArray.length ; i++){
			taskArray.push(
				{ start : startDateArray[i],
                end : deadlineArray[i],
                name : parentTaskName[i],
                id : parentTaskNo[i],
                progress : Number(parentProgress[i])
                })
		}
		
		for(let i = 0; i < childTaskList.length; i++){
			taskArray.push({ start : childStartDateArr[i],
                end : childDeadlineArr[i],
                name : childTaskName[i],
                id : childTaskNoArr[i],
                progress : Number(childTaskProgressionArr[i]),
                dependencies : childDependenciesArr[i]
                })
		}
		
		var gantt_chart = new Gantt(".gantt-target", taskArray, {
			on_click : function(task) {
				console.log(task);
			},
			on_date_change : function(task, start, end) {
				console.log(task, start, end);
			},
			on_progress_change : function(task, progress) {
				console.log(task, progress);
			},
			on_view_change : function(mode) {
				console.log(mode);
			},
			view_mode : 'Day',
			language : 'en'
		});
		
		console.log(gantt_chart);
		
		
	</script>
	
</body>
</html>