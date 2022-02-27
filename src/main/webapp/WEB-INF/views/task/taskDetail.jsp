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



<style>
#box1header{
	margin:30px;
	font-size: 18px;
	font-weight:600;
}
.box{
	width: 1600px !important;
	height : 800px;
	margin : 30px;
	display: flex;
}
.box2{
	width:1400px;
	height: 800px;
	margin-left:50px;
}
#box1_body{
	font-size : 18px;
	width:180px;
	
}
.upDown { 
	border: 0.2px solid gray;
	overflow: visible;
	width:0.3px;
	height:800px;
	display:inline-box;
	
}
body {
			font-family: sans-serif;
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
				<%-- 상위업무 forEach --%>

				<c:forEach var="parentTask" items="${ requestScope.parentTaskList }"
					varStatus="status">
					<label id="parentTaskName" type="button" class="folder_toggle"
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
	<script>
	
	
	
	<%-- hover event 추가 --%>
	$(".folder_toggle").hover(function(){
		$(this).css({"border" : "solid",
				    "border-color" : "gray",
				    "border-radius" : "5px",
				    "border-width" : "1px"});
		
	}, function(){
		$(this).css({"border-color" : "white"});
	});
	
	$(".childName").hover(function(){
		$(this).css({"border" : "solid",
		    "border-color" : "gray",
		    "border-radius" : "5px",
		    "border-width" : "1px"});

	}, function(){
		$(this).css({"border-color" : "white"});
	});
	<%-- hover 이벤트 끝 --%>
	
	const startDate = "${parentTaskList[0].startDate}";
	console.log(startDate);
	
	var parentTaskList = "${parentTaskList}";
	console.log(parentTaskList.length);
	
	 var TaskList=new Array();
	  <c:forEach items="${requestScope.parentTaskList}" var="task">
	  	var startDate ="${task.startDate}";
	  </c:forEach>
	   
	   console.log(TaskList);
	
		var tasks = [
			
			
			
			{
				start: startDate,
				end: '2022-02-28',
				name: 'Redesign website',
				id: "Task 0",
				progress: 20
			},
			{
				start: startDate,
				end: '2022-02-28',
				name: 'Write new content',
				id: "Task 1",
				progress: 5,
				dependencies: 'Task 0'
			},
			{
				start: startDate,
				end: '2022-02-28',
				name: 'Apply new styles',
				id: "Task 2",
				progress: 10,
				dependencies: 'Task 1'
			},
			{
				start: startDate,
				end: '2022-02-28',
				name: 'Review',
				id: "Task 3",
				progress: 5,
				dependencies: 'Task 2'
			},
			{
				start: startDate,
				end: '2022-02-28',
				name: 'Deploy',
				id: "Task 4",
				progress: 0,
				dependencies: 'Task 2'
			},
			{
				start: startDate,
				end: '2022-02-28',
				name: 'Go Live!',
				id: "Task 5",
				progress: 0,
				dependencies: 'Task 4',
				custom_class: 'bar-milestone'
			},
			{
				start: startDate,
				end: '2022-02-28',
				name: 'Long term task',
				id: "Task 6",
				progress: 0
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
</html>