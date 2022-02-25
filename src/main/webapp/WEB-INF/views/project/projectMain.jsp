<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- jQuery library -->
<script
   src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>

<!-- Latest compiled JavaScript -->
<link rel="stylesheet"
   href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
   integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
   crossorigin="anonymous">

<!-- Google Material Icons-->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
   href="https://fonts.googleapis.com/icon?family=Material+Icons">

<!-- chart -->
<script
   src="https://cdn.anychart.com/releases/8.10.0/js/anychart-core.min.js"></script>
<script
   src="https://cdn.anychart.com/releases/8.10.0/js/anychart-pie.min.js"></script>

<style>
#output_header {
   font-size: 1.6rem;
   width: 100%;
   height: 100%;
   margin-left: 5%;
   margin-top: 5%;
}

.main-box {
   border: solid;
   width: 27%;
   height: 100%;
   border-radius: 20px;
   border-color: #C4C4C4;
   float: left;
   margin-right: 30px;
}

.main-box2 {
   border: solid;
   width: 17%;
   height: 100%;
   border-radius: 20px;
   border-color: #C4C4C4;
   float: left;
   margin-right: 100px;
}
.main-box3 {
   border: solid;
   width: 17%;
   height: 100%;
   border-radius: 20px;
   border-color: #C4C4C4;
   float: left;
   margin-left: 11%;
}

.donut-chart {
   width: 100%;
   height: 100%;
   margin: 0;
   padding: 0;
}

#chartContent {
   width: 1700px;
   height: 250px;
}

#chartContent2 {
   width: 1700px;
   height: 250px;
}

.project-name {
   margin-right: 370px;
   display: inline-block;
}
.project-issue{
   margin-right: 370px;
   display: inline-block;
}

.project-name2 {
   display: inline-block;
   margin-top: 10px;
}

button {@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");-webkit-appearance: none;
            -moz-appearance: none ;
            appearance: none ;

            margin: 0; padding: 0.7rem 0.2rem ;

            font-family: "Noto Sans KR ", sans-serif ;
            font-size: 1.3rem ;
            font-weight: 400; text-align: center ;
            text-decoration: none ;

            display: inline-block ;
            width: auto ;

            border: none ;
            border-radius: 4px ;

            cursor: pointer ;
            transition: 0.5s ;

            box-shadow: 3px 3px 3px gray ;
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

#addButton1 {
   background-color: transparent;
   color: black;
   margin:0px;
   
}

.button2 {
   background-color: transparent;
   color: black;
   border: none;
   margin:0px;
   
}

#noticebutton{
   border: none;
   cursor: pointer ;
    transition: 0.5s ;
}

textarea {
   width: 100%;
   height: 500px;
   padding: 10px;
   box-sizing: border-box;
   border: solid 2px #B4ADAD;
   border-radius: 5px;
   font-size: 14px;
   resize: both;
}

#title {
   width: 80%;
   height: 30px;
   border-radius: 5px;
   border: solid 2px #B4ADAD;
}
</style>



<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>

	<div >
      <main>
         <div id="output_header">
            
            <%-- 각 파트별로 한번에 div 로 묶어서 한게 아니라 각 상자 따로 헤더(프로젝트1, 이슈, 프로젝트가이드 등등) 따로 입니다,,, margin-left로 글씨 넣어줌...ㅎ  --%>
             <%-- 메인화면 헤더 --%>
            <label><i style='font-size: 24px' class='fas'>&#xf4fe;</i>프로젝트 이름</label>
            <hr style="width:1470px;">
            
            <%-- 프로젝트이름  --%>
            <label class="project-name">프로젝트 1</label>
            
            <label class="project-issue">이슈</label>
            
            <%-- 프로젝트 가이드 시작 --%>
            <label style="margin-left: 11%">&emsp;&emsp;&emsp;프로젝트 가이드</label>
            <%-- + 버튼 --%>
            <button class="float" id="addButton1">
               <i style='font-size: 16px' class='fas'>&#xf055;</i>
            </button>
            <%-- 프로젝트 가이드 끝 --%>
            
            <div id="chartContent">
            
               <%-- 프로젝트 1 해당 박스 --%>
               <div class="main-box">
                  <div class="donut-chart" id="container"></div>
               </div>
               
               <%-- 이슈 해당 박스 --%>
               <div class="main-box">
                  <div class="donut-chart" id="container2"></div>
               </div>
            	   
               <div class="main-box3">
               sdfds<br>
               sdfds<br>
               sdfds<br>
               sdfds<br>
               sdfds<br>
               sdfds<br>
               </div>
               
            </div>
            
            <%-- To Do 시작 --%>
            <label class="project-name2">To Do</label>
            <button class="float button2" style="margin-right:300px;">
               <i style='font-size: 16px' class='fas'>&#xf055;</i>
            </button>
            <%-- To Do 끝 --%>
            
            <%-- 공지사항 시작 --%>
            <label class="project-name2 ">공지사항</label>
            <button class="btn  float "
               data-bs-toggle="modal" data-bs-target="#exampleModal" style="margin-right:235px;" id="noticebutton">
               <i style='font-size: 16px' class='fas'>&#xf055;</i>
            </button>
            <%-- 공지사항 끝 --%>
            
            <%-- 회의록 시작 --%>
            <label class="project-name2">회의록</label>
            <button class="float button2" style="margin-right:280px;" onclick="location.href='${ pageContext.servletContext.contextPath }/meeting/list'">
               <i style='font-size: 16px' class='fas'>&#xf055;</i>
            </button>
            <%-- 회의록 끝 --%>
            
            <%-- 교육 시작 --%>
            <label class="project-name2">교육</label>
            <button class="float button2">
               <i style='font-size: 16px' class='fas'>&#xf055;</i>
            </button>
            <%-- 교육 끝 --%>
            
            <div id="chartContent2">
               <%--To Do 박스--%>
               <div class="main-box2"></div>
               <%--공지사항 박스 --%>
               <div class="main-box2"></div>
               <%--회의록 박스 --%>
               <div class="main-box2"></div>
               <%-- 교육 박스 --%>
               <div class="main-box2"></div>
            </div>
         </div>
      </main>
      <br clear="all">
   </div>
	
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	
	<script>
      anychart.onDocumentReady(function() {
               // add data(도넛 차트 각각의 업무 개수 - 개수 다 더해서 퍼센트로 나타내줍니다)
               var data = anychart.data.set([ [ '진행중 업무', 20 ],
                     [ '완료 업무', 30 ], [ '진행 전 업무', 30 ], [ '중단 업무', 20 ]

               ]);

               // create a pie chart with the data (위에서 저장한 data를 가져와서 chart 생성)
               var chart = anychart.pie(data);

               // set the chart radius making a donut chart()
               chart.innerRadius('40%')

               // create a color palette(차트 각 파트별 색상 지정, data의 종류 개수와 일치해야합니다! data가 4개니까 색상도 4개 지정, 같은색으로 지정 가능))
               var palette = anychart.palettes.distinctColors();

               // set the colors according to the brands
               palette.items([ {
                  color : '#E1BEE7'
               }, {
                  color : '#CE93D8'
               }, {
                  color : '#B39DDB'
               }, {
                  color : '#D2B4DE'
               },

               ]);

               // apply the donut chart color palette(위에서 저장한 컬러를 차트에 적용합니다)
               chart.palette(palette);

               // set the position of labels(화면을 보면 도넛 밖으로 20.0%라고 되어있을텐데 그거에 대한 설정입니다)
               chart.labels().position("outside");

               // disable the legend
               chart.legend(false);

               // format the donut chart tooltip( 퍼센트로 계산해 준다는 거 같습니다)}
               chart.tooltip().format('value: {%PercentValue}%');

               // create a standalone label
               var label = anychart.standalones.label();

               // configure the label settings
               label
                     .useHtml(true)
                     .text(
                           '<span style = "color: #313136; font-size:15px;">project</span>')
                     .position('center').anchor('center').hAlign(
                           'center').vAlign('middle');

               // set the label as the center content
               chart.center().content(label);

               // set container id for the chart(어느 박스에 차트를 넣어줄건지 적어줍니다.199번째 줄)
               chart.container('container');

               // initiate chart drawing
               chart.draw();

            });
      <%-- chart 생성 반복 --%>
      anychart.onDocumentReady(function() {

               // add data
               var data2 = anychart.data.set([ [ '발생이슈', 5 ],
                     [ '완료된이슈', 10 ], [ '진행중 이슈', 20 ] ]);

               // create a pie chart with the data
               var chart = anychart.pie(data2);

               // set the chart radius making a donut chart
               chart.innerRadius('40%')

               // create a color palette
               var palette = anychart.palettes.distinctColors();

               // set the colors according to the brands
               palette.items([ {
                  color : '#DD9595'
               }, {
                  color : '#E5E298'
               }, {
                  color : '#92B1AA'
               }

               ]);

               // apply the donut chart color palette
               chart.palette(palette);

               // set the position of labels
               chart.labels().position("outside");

               // disable the legend
               chart.legend(false);

               // format the donut chart tooltip
               chart.tooltip().format('value: {%PercentValue}%');

               // create a standalone label
               var label = anychart.standalones.label();

               // configure the label settings
               label
                     .useHtml(true)
                     .text(
                           '<span style = "color: #313136; font-size:15px;">Issue</span>')
                     .position('center').anchor('center').hAlign(
                           'center').vAlign('middle');

               // set the label as the center content
               chart.center().content(label);

               // set container id for the chart(204번째 줄 박스에 넣음)
               chart.container('container2');

               // initiate chart drawing
               chart.draw();

            });

      //Modal
      var myModal = document.getElementById('myModal')
      var myInput = document.getElementById('myInput')

      myModal.addEventListener('shown.bs.modal', function() {
         myInput.focus()
      })
   </script>
</body>
</html>