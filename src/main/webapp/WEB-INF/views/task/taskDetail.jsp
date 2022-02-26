<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
	crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<script src="frappe-gantt.min.js"></script>
<link rel="stylesheet" href="frappe-gantt.css">
<style>
#box1header{
	margin:30px;
	font-size: 18px;
	font-weight:600;
}
.box{
	width: 1600px !important;
	height : 800px;
	background-color : orange;
	margin : 30px;
	display: flex;
}
.box2{
	width:1400px;
	height: 800px;
	background-color: blueviolet;
	margin-left:50px;
}
#box1_body{
	font-size : 18px;
	width:180px;
	
}
.upDown { 
	border: 1px solid black;
	overflow: visible;
	width:0.3px;
	height:800px;
	display:inline-box;
	
}

</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp" />
	<div class="taskList">
		<div id="box1header">
			업무 관리 <br>
		</div>
		<hr>
		<div class="box">
			<div id="box1_body">
				상위업무 forEach

				<c:forEach var="parentTask" items="${ requestScope.parentTaskList }"
					varStatus="status">
					<label id="parentTaskName" type="button" class="folder_toggle"
						data-toggle="collapse" data-target="#demo${ status.index }"><i
						style='font-size: 16px;' class='fas'>&#xf07b;</i>&nbsp;<c:out
							value="${ parentTask.taskCategory.categoryName }" /></label>
					<br>
					하위 업무 forEach
					<c:forEach var="childList"
						items="${ requestScope.parentTaskList[ status.index ].childList }"
						varStatus="st">

						<div id="demo${ status.index }" class="collapse">
							<div id="childTasks${ st.index }">
								<div id="childTask">
									<label class=childName type="button"><i
										style='font-size: 24px' class='fas'>&#xf0da;</i> <c:out
											value="${ childList.taskCategory.categoryName }" /></label> <input
										id="childDTO${ st.index }" type="hidden"
										value="${ childList.taskNo }">
								</div>
							</div>
						</div>
					</c:forEach>
					<c:out value="${ parentTask.taskCategory.categoryName }"/><br>
				</c:forEach>
			</div>
			<div class="upDown"></div>
			<div class="box2"></div>
		</div>
		
	</div>
	<div class="box2">
		<div class="container">
		<h2>Interactive Gantt Chart entirely made in SVG!</h2>
		<div class="gantt-target"></div>
	</div>
	</div>
	<script>
	var tasks = [
		{
			start: '2021-12-20',
			end: '2021-12-25',
			name: '요구사항분석',
			id: "Task 0",
			progress: 100
		},
		{
			start: '2021-12-26',
			end: '2022-01-01',
			name: '고객 요구사항',
			id: "Task 1",
			progress: 100,
			dependencies: 'Task 0'
		},
		{
			start: '2022-01-02',
			end: '2022-01-10',
			name: '분석',
			id: "Task 2",
			progress: 70,
			dependencies: 'Task 1'
		},
		{
			start: '2022-01-11',
			end: '2022-01-15',
			name: 'DB설계',
			id: "Task 3",
			progress: 100,
			dependencies: 'Task 2'
		},
		{
			start: '2022-01-16',
			end: '2022-01-21',
			name: 'ERD 제작',
			id: "Task 4",
			progress: 80,
			dependencies: 'Task 2'
		}
	]
	var gantt_chart = new Gantt(".gantt-target", tasks, {
		on_click: function (task) {
			console.log(task);
		},
		on_date_change: function(task, start, end) {
			console.log(task, start, end);
		},
		on_progress_change: function(task, progress) {
			console.log(task, progress);
		},
		on_view_change: function(mode) {
			console.log(mode);
		},
		view_mode: 'Day',
		language: 'en'
	});
	console.log(gantt_chart);

	</script>
</body>
</html> --%>