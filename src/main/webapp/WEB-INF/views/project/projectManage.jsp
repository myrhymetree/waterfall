<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

</body>
</html>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>
			<div>
				<main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4"><i class="fas fa-project-diagram"></i> 프로젝트</h1>
                        <div class="mb-2">
                            <div class="col" style="width: 50%; text-align: left;">
								<button type="button" onclick="location.href='메인 프로젝트화면.html'" class="btn btn-dark">프로젝트 관리</button>
                            </div>
                        </div>
                        <div class="card mb-4 mt-3">
                            <div class="card-header" style="width: 100%;">
                                <div class="row">
                                    <div class="col" style="width: 50%; text-align: left; font-weight: bold; font-size: 1.3em">
                                        <label>관리중인 프로젝트</label>
                                    </div>
                                    
                                </div>
                            </div>
                            <div class="card-body">
                                <table style="width:100%; text-align: center;">
                                    <colgroup>
                                        <col style="width:20%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>

                                    </colgroup>

                                    <thead>
                                        <tr>
                                            <th>프로젝트명</th>
                                            <th>담당자</th>
                                            <th>진행률</th>
                                            <th>산출물</th>
                                            <th>이슈</th>
                                            <th>시작일</th>
                                            <th>마감일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="project" items="${ manageProject }">
                                        	<tr>
	                                        	<th><c:out value="${ project.name}" /></th>
	                                            <th><c:out value="${ project.member.memberName}" /></th>
	                                            <th><c:out value="${ project.progression }" /></th>
	                                            <th>대기</th>
	                                            <th>대기</th>
	                                            <th><c:out value="${ project.startDate }" /></th>
	                                            <th><c:out value="${ project.deadLine }" /></th>
                                        	</tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="card mb-4 mt-3">
                            <div class="card-header">
                                <label style="font-size: 1.3em; font-weight: bold">참여중인 프로젝트</label>
                            </div>
                            <div class="card-body">
                                <table style="width:100%;text-align: center;">
                                    <colgroup>
                                        <col style="width:20%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>

                                    </colgroup>

                                    <thead>
                                        <tr>
                                            <th>프로젝트명</th>
                                            <th>담당자</th>
                                            <th>진행률</th>
                                            <th>산출물</th>
                                            <th>이슈</th>
                                            <th>시작일</th>
                                            <th>마감일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="project" items="${ joinProject }">
                                        	<tr>
	                                        	<th><c:out value="${ project.name}" /></th>
	                                            <th><c:out value="${ project.member.memberName}" /></th>
	                                            <th><c:out value="${ project.progression }" /></th>
	                                            <th>대기</th>
	                                            <th>대기</th>
	                                            <th><c:out value="${ project.startDate }" /></th>
	                                            <th><c:out value="${ project.deadLine }" /></th>
                                        	</tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
						</div>
	                </main>
				</div>
			</div>
	<script>
	
    $('#datatablesSimple tbody tr').click(function () {  
        // get position of the selected row  
        var position = $('#datatablesSimple').dataTable().fnGetPosition(this)  
        // value of the first column (can be hidden)  
        var id = $('#datatablesSimple').dataTable().fnGetData(position).id
        // redirect
        document.location.href = "프로젝트수정페이지.html";
    })


    
    if(document.getElementsByTagName("td")) {
        
        const $tds = document.getElementsByTagName("td");
        for(let i = 0; i < $tds.length; i++) {
            
            $tds[i].onmouseenter = function() {
                this.parentNode.style.backgroundColor = "rgba(29, 22, 22, 0.106)";
                this.parentNode.style.cursor = "pointer";
            }
            
            $tds[i].onmouseout = function() {
                this.parentNode.style.backgroundColor = "white";
                this.parentNode.style.color = "black"
            }
            
            $tds[i].onclick = function() {
                const no = this.parentNode.children[0].innerText;
                location.href = "${ pageContext.servletContext.contextPath }/board/detail?no=" + no;
            }
            
        }
        
    }
        

	</script>



</body>
</html>