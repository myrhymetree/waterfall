<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawStuff);

      function drawStuff() {
        var data = new google.visualization.arrayToDataTable([
          ['Move', 'Percentage'],
          ["전체 업무", 44],
          ["진행중 업무", 20],
          ['테스트 업무', 4],
		  ["완료 업무", 20]
        ]);

        var options = {
          width: 500,
          legend: { position: 'none' },
          chart: {
            title: '프로젝트 업무 현황',
            subtitle: '배정 업무 진행 현황' },
          axes: {
            x: {
              0: { side: 'top'} // Top x-axis.
            }
          },
          bar: { groupWidth: "20%" }
        };

        var chart = new google.charts.Bar(document.getElementById('top_x_div'));
        // Convert the Classic options to Material options.
        chart.draw(data, google.charts.Bar.convertOptions(options));
      };
    </script>



<title>Insert title here</title>

<style>
/* 관리 프로젝트 프로젝트정보 css */
.manage-project-info {
	border: 1px solid black;
	margin-top: 3%;
	border-radius: 5px;
	height: 180px;
}

.manage-project-info-head{
	height: 32px;
	border-radius: 3%;
	border: 1px #343A40 solid;
	background-color: #343A40;
	color: #bebebe;
}
.manage-project-info-head > label {
    margin-left: 2%;
    margin-top: 1px;
    font-size: 1.08em;
    font-weight: 1000;
}

.manage-project-info-body > label {
	width: 30%;
	margin-left: 2%;
	font-size: 0.9em;
	font-weight: 800;
 }
.manage-project-info-body > input {
	margin-left: 2%;
	width:150px; 
	border: none; 
	background: transparent;
 }

/* 관리 프로젝트 원형차트 css부분 */
.manageprojectbox {
	margin-top: 6%;
	width: 90%;
	border: 1px solid black;
	border-radius: 3px;
}


.mpb-chart-head {
	height: 32px;
	border-radius: 3%;
	border: 1px #343A40 solid;
	background-color: #343A40;
	color: #bebebe;
	font-size: 1em;
	font-weight: 1000;
}
.mpb-chart-head > label {
	margin-left: 3%;
	margin-top: 1%;
}
.manage-projectbox-body > label{
	margin: 2%;	
	font-size: 0.9em;
	font-weight: 700;
	width: 100%;
}

.manage-projectbox-body > label > input{
	border: none; 
	background: transparent;
	width:50px;
	margin-left: 5%;
}

/* 참여중인 프로젝트 상세정보 css */
.join-project{
	border:#343A40 1px solid;
	border-radius: 5px;
	height: 265px;
	margin-top: 2%;
}
.join-project-head {
	height: 32px;
	border-radius: 3%;
	border: 1px #343A40 solid;
	background-color: #343A40;
	color: #bebebe;
	font-size: 1.1em;
	font-weight: 1000;
}
.join-project-head > label{
	margin-top: 2px;
	margin-left: 2%;
}

.join-project-body > label{
	margin: 2%;	
	margin-left: 2%;
	font-size: 0.9em;
	font-weight: 700;
	width: 100%;
}

.join-project-body > label > input{
	border: none; 
	background: transparent;
	width: auto;
	margin-left: 5%;
}


.developer-main-table {
	border: 1px solid black;
	height: 90%;
	margin: 2%;
	margin-left: 5%;
	border-radius: 5px;
}
.thead-css {
		border-radius: 3%;
		background-color: #343A40;
		color: #bebebe;
}



.project-category-label{
	margin-top: 2%;
}
.project-category-label > label {
	margin-left: 2%;
	font-size: 1.1em;
	font-weight: 1000;
}

.develop-main-header{
 	height: 100px;
 }
 .develop-main-header > div > label{
	text-align: center;
	font-size: 1.5em;
	font-weight: 1000;
	margin-left: 2%;
	
 }

 .develop-main-body-top{
	 
	 height: 300PX;
 }


</style>

</head>
<body style="overflow-x:hidden; overflow-y:auto;">

	<jsp:include page="/WEB-INF/views/common/header.jsp"/>

	
	<div class=" develop-main-header">
		<div class="mt-5 ">
			<label>${ sessionScope.loginMember.name } 님 환영합니다</label>	
		</div>
	</div>
	
	<div class=" project-category-label">
		<label>관리중인 프로젝트</label>
	</div>
	<!-- 관리중인 프로젝트 폼-->
	<div class="mt-2 row  develop-main-body-top">
		<div class=" col-4">
			<div class="developer-main-table">
				<table style="width: 100%;font-size:1.1em;">
					<colgroup>
						<col style="width: 15%" />
						<col style="width: 65%" />
						<col style="width: 20%" />
						
					</colgroup>
					<thead class="thead-css">
						<th></th>
						<th>프로젝트명</th>
						<th>PM이름</th>
						
					</thead>
					<tbody id="project-list">
						<c:forEach var="project" items="${ requestScope.projectList }" varStatus="status">
							<tr>
								<td><input readonly type="hidden" value="${ project.no }"></td>
								<td><c:out value="${ project.name }"/></td>
								<td><c:out value="${ project.member.memberName }"/></td>
							</tr>
						</c:forEach>							
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/views/main/mainpaging.jsp"/>
			</div>
		</div>



		<div class=" col-3 ">
			<div class="manage-project-info">
				<div class="manage-project-info-head">
					<label>프로젝트 정보</label>
				</div>
				<div class="manage-project-info-body mt-2 ">
					<label>시작일 : </label><input readonly type="date" value="2022-01-01"><br>
					<label>마감일 : </label><input readonly type="date" value="2022-03-14"><br>
					<label>&nbsp; 업무 &nbsp;: </label><input readonly type="text" value="1개"><br>
					<label>&nbsp; 이슈 &nbsp;: </label><input readonly type="text" value="1개"><br>
					<label>산출물 : </label><input readonly type="text" value="1개"><br>
				</div>
			</div>
		</div>
		
		
		
		<div class=" col-5">
		<div class="row" style="height: 100%;">
			<div class="col-8 " id="manage-info-chart"></div>
			
			<div class="col-4">
				<div class="manageprojectbox">
					<div class="mpb-chart-head" >
						<label class="" id="manage-project-info-category">이슈</label>							
					</div>
					<div class="manage-projectbox-body">
						<label class="">대기중 : <input readonly type="text" id="issue-category-wait"></label><br>
						<label class="">처리중 : <input readonly type="text" id="issue-category-proc"></label><br>
						<label class="">해결완료 : <input readonly type="text" id="issue-category-solv"></label><br>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>

	<div class=" project-category-label">
		<label>참여중인 프로젝트</label>
	</div>

	

	<!-- 참여중인 프로젝트 폼-->
	<div class="mt-2 row  develop-main-body-top">
		<div class=" col-4">
			<div class="developer-main-table">
				<table style="width: 100%;font-size:1.1em;">
					<colgroup>
						<col style="width: 15%" />
						<col style="width: 65%" />
						<col style="width: 20%" />
						
					</colgroup>
					<thead class="thead-css">
						<th></th>
						<th>프로젝트명</th>
						<th>PM이름</th>
						
					</thead>
					<tbody id="project-list">
						<c:forEach var="project" items="${ requestScope.projectList }" varStatus="status">
							<tr>
								<td><input readonly type="hidden" value="${ project.no }"></td>
								<td><c:out value="${ project.name }"/></td>
								<td><c:out value="${ project.member.memberName }"/></td>
							</tr>
						</c:forEach>							
					</tbody>
				</table>
				<div calss="mt-4">
					<jsp:include page="/WEB-INF/views/main/subpaging.jsp"/>
				</div>
			</div>
		</div>
	
		<div class=" col-3">
			<div class="join-project">
				<div class="join-project-head">
					<label>프로젝트 이름</label>
				</div>
				<div class="join-project-body">
					<label>프로젝트 PM : <input readonly type="text" value="박성준"></label><br>
					<label>프로젝트 생성일 : <input readonly type="date" value="2022-01-01"></label><br>
					<label>프로젝트 참여일 : <input readonly type="date" value="2022-01-01"></label><br>
					<label>프로젝트 역할 : <input readonly type="text" value="DBA QA 테스터"></label><br>
				</div>
			</div>
		</div>
	
	
	
		<div class=" col-5">
			<div id="top_x_div" style="width: 100%; height: 100%;"></div>

		</div>
	</div>


	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>




	<script>
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawmanageinfoChart);
		
		function drawmanageinfoChart() {

			var data = google.visualization.arrayToDataTable([
				['Status', 'Amount'],
				['대기중', 2],
				['처리중', 2],
				['완료', 2]
			]);
			
			var options = {
				title: '이슈',
				slices: {
				0: { color: 'yellowgreen' },
				1: { color: 'orangered' },
				2: { color: 'cyan' }
				},
				sliceVisibilityThreshold:0
		
			
			};
			
			var chart = new google.visualization.PieChart(document.getElementById('manage-info-chart'));
			
			chart.draw(data, options);
		}

</script>

</body>
</html>