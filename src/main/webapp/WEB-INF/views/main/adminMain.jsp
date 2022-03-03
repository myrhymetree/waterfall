<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawissueChart);
	  google.charts.setOnLoadCallback(drawtaskChart);

      function drawissueChart() {

        var data = google.visualization.arrayToDataTable([
          ['Status', 'Amount'],
          ['대기중', 3],
          ['해결완료', 9]
        ]);

        var options = {
          title: '이슈',
          slices: {
            0: { color: 'yellowgreen' },
            1: { color: 'orangered' }
          }
        };

        var chart = new google.visualization.PieChart(document.getElementById('issuechart'));

        chart.draw(data, options);
      }

		function drawtaskChart() {

		var data = google.visualization.arrayToDataTable([
		['Status', 'Amount'],
		['진행중', 15],
		['완료',  9]
		]);

		var options = {
		title: '업무',
          slices: {
            0: { color: 'yellowgreen' },
            1: { color: 'orangered' }
          }
		};

		var chart1 = new google.visualization.PieChart(document.getElementById('taskchart'));

		chart1.draw(data, options);
	}



	</script>

<style>
	
	.history-area{
		height: 70%;
		overflow: auto;
	}
	.history-area > h5{
		margin-top: 10px;
	}
	th {
		height: 3em;
	}
	tr {
		height: 2em;
	}
	hr {
		height: 2px;
		background-color: goldenrod;
	}
	.print-area {
		border: 3px solid black;
		width: 15%;
		height: 300px;
		
	}
	.inbox {
		margin-left: 1%;
		width: 99%;
		height: 100%;
		border: 3px solid red;
		border-radius: 2%;
	}
	
</style>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	
	<div style="margin:1%">
		<div class="row" style="border: 1px; height:20px">
			<h3>관리자님 환영합니다</h3>
			<hr style="border: 1px;">
		</div>
		<div class="row" style="height: 40px;"></div>
		<div class="row ">
			<div id="test" class="col-4 print-area">
				<div class="inbox">
					<div style="height: 80%;">
						<table style="width: 100%;font-size:1.1em; border: 1px solid rebeccapurple">
							<colgroup>
								<col style="width: 15%" />
								<col style="width: 65%" />
								<col style="width: 20%" />
								
							</colgroup>
							<thead>
								<th></th>
								<th>프로젝트명</th>
								<th>PM이름</th>
								
							</thead>
							<tbody id="project-list">
								<c:forEach var="project" items="${ requestScope.projectList }" varStatus="status">
									<tr>
										<td><input type="hidden" value="${ project.no }"></td>
										<td><c:out value="${ project.name }"/></td>
										<td><c:out value="${ project.member.memberName }"/></td>
									</tr>
								</c:forEach>							
							</tbody>
						</table>
					</div>
					<div class="mt-4" style="border: 2px solid black; text-align: center;">
						<jsp:include page="/WEB-INF/views/main/mainadminpaging.jsp"/>
					</div>1
				</div>
			</div>
			<div id="test" class="col-3 print-area">
				<div class="inbox" style="font-size: 1.2em;">
					<input type="text" value="5번프로젝트" style="border: none; background: transparent;"><br>
					<label class="mt-2">PM : <input type="text" value="홍성원" style="width:100px; border: none; background: transparent;"></label><br>
					<label class="mt-2">시작일 : <input type="text" value="2022/02/14" style="width:180px; border: none; background: transparent;"></label>
					<label class="mt-2">마감일 : <input type="text" value="2022/03/14" style="width:180px; border: none; background: transparent;"></label>
					<label class="mt-2">상태 : <input type="text" value="진행중" style="width:180px; border: none; background: transparent;"></label>
					<label class="mt-2">진행률 : <input type="text" value="30%" style="width:180px; border: none; background: transparent;"></label>
					<label class="mt-2">산출물 : <input type="text" value="3개" style="width:180px; border: none; background: transparent;"></label>
				</div>
				
			</div>
			<div id="test" class="col-5 print-area">
				<div class="inbox">
					<h2 class="mt-3">히스토리</h2>
					<div class="history-area">
						<h5>[김서영]님이 [요구사항분석]업무를 [생성]했습니다.</h5>
						<h5>[차화응]님이 [홍성원]님을 [5번프로젝트]프로젝트에 [배정]했습니다.</h5>
						<h5>[박성준]님이 [논리데이터모델링]업무에 [이슈]를 [등록]했습니다.</h5>
						<h5>[김영광]님이 [5번프로젝트]프로젝를 [수정]했습니다.</h5>
						<h5>[홍성원]님이 [김서영]님을 프로젝트에 [배정]]했습니다.</h5>
						<h5>[김서영]님이 [요구사항분석]업무를 [생성]했습니다.</h5>
						<h5>[차화응]님이 [홍성원]님을 [5번프로젝트]프로젝트에 [배정]했습니다.</h5>
						<h5>[박성준]님이 [논리데이터모델링]업무에 [이슈]를 [등록]했습니다.</h5>
						<h5>[김영광]님이 [5번프로젝트]프로젝를 [수정]했습니다.</h5>
						<h5>[홍성원]님이 [김서영]님을 프로젝트에 [배정]]했습니다.</h5>
					</div>
				</div>
			</div>
		
		</div>
	</div>
	<div class="row print-area" style="width: 98%; height: 100%;margin-left: 1%">
		<div class="col-6" style="border: 1px solid red;">
			<div class="row" style="border: 1px solid; height:100%">  
				<div class="col-8" id="issuechart" style="width: 80%; height: 80%;"></div>
				<div class="col">
					<br>
					<label class="mt-2" >총 이슈 <br><input type="text" value="12개" style="width:70px; border: none; background: transparent;"></label><br>
					<label class="mt-2">대기중인 이슈 <input type="text" value="3개" style="width:70px; border: none; background: transparent;"></label><br>
					<label class="mt-2">해결된 이슈 <input type="text" value="9개" style="width:70px; border: none; background: transparent;"></label>	<br>
				</div>
			</div>
			
		</div> 
		<div class="col-6" style="border: 1px solid red">
			<div class="row" style="border: 1px solid; height:100%">  
				<div class="col-8" id="taskchart" style="width: 80%; height: 80%;"></div>
				<div class="col">
					<label class="mt-2" >총 업무 <input type="text" value="24개" style="width:100px; border: none; background: transparent;"></label>
					<label class="mt-2">진행중 업무 <input type="text" value="15개" style="width:100px; border: none; background: transparent;"></label>
					<label class="mt-2">완료 업무<input type="text" value="9개" style="width:100px; border: none; background: transparent;"></label>
				</div>
			</div>
		</div> 
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
	<script>
		$(function() {
			$("#project-list td").hover(function() {
				$(this).parent().css({"background":"rgba(29, 22, 22, 0.106)", "cursor":"pointer"});
			}).mouseleave(function() {
				$(this).parent().css({"background":"white", "color":"black"});
				
			}).click(function() {
				$projectNo = this.parentNode.children[0].children[0].value;
				console.log($projectNo);
				
				$.ajax({
					type: "get",
					url: "${ pageContext.servletContext.contextPath }/menu/main/admin/project/" + $projectNo,
					success: function(data) {
						alert('컨트롤러 갔다왔어요~');
					},
					error: function(xhr, status, error) {
						alert('admin mainpage/ find project list /network error');
					}
						
				});
			});
		});
	</script>
	
	
	
	
	
</body>
</html>









































