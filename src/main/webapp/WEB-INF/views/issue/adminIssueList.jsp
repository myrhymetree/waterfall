<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IssueList</title>
<script src="${ pageContext.servletContext.contextPath }/resources/assets/demo/chart-area-demo.js"></script>
<script src="${ pageContext.servletContext.contextPath }/resources/assets/demo/chart-bar-demo.js"></script>
<script src="${ pageContext.servletContext.contextPath }/resources/js/datatables-simple-demo.js"></script>
<script src="${ pageContext.servletContext.contextPath }/resources/assets/demo/chart-area-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" data-auto-replace-svg="nest"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
<style>
	td { vertical-align: middle !important;
	     text-align: center;
	   }
	th { text-align: !center; }
</style>
</head>
<body>

	<c:choose>
 		<c:when test="${ sessionScope.loginMember.role eq 1 }"><jsp:include page="../common/header.jsp"/></c:when>
		<c:otherwise><jsp:include page="../common/inprojectheader.jsp"/></c:otherwise>
	</c:choose>
	
<jsp:include page="/WEB-INF/views/issue/adminModal.jsp"/>
<jsp:include page="/WEB-INF/views/issue/adminDetailModal.jsp"/>
     <main>
         <div class="container-fluid px-4">
             <br>
             <br>
             <h1 class="mt-4"><i class="fas fa-exclamation-circle"></i>이슈관리</h1>
             <div class="card mb-4" style="vertical-align: middle;">
                 <div class="card-header">
                     <i class="fas fa-table me-1"></i>
                     	업무 당 이슈 목록
					 <button class="btn btn-outline-dark" data-toggle="modal" data-target="#registModal" id="registModal" style="float: right;">등록</button>
                 </div>
                 <div class="card-body">
                     <table id="datatablesSimple">
                        <colgroup>
                             <col style="width:5%"/>
                             <col style="width:15%"/>
                             <col style="width:15%"/>
                             <col style="width:15%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                         </colgroup>
                         <thead>
                             <tr style="text-align: center;">
                                 <th style="text-align: center;">No</th>
                                 <th style="text-align: center;">이슈 명</th>
                                 <th style="text-align: center;">업무 명</th>
                                 <th style="text-align: center;">발생일</th>
                                 <th style="text-align: center;">상태</th>
                                 <th style="text-align: center;">중요도</th>
                                 <th style="text-align: center;">이슈제기자</th>
                                 <th style="text-align: center;">이슈담당자</th>
                                 <th style="text-align: center;">이슈배정</th>
                             </tr>
                         </thead>
                         <tbody>
                         	<c:forEach var="issue" items="${ requestScope.issueList }" varStatus="status">
                             <tr id="listArea" class="issueSelect">
                                 <td><c:out value="${ issue.no }"/></td>
                                 <td><c:out value="${ issue.name }"/></td>
                                 <td><c:out value="${ issue.taskCode.taskCategoryName }"/></td>
                                 <td><c:out value="${ issue.createdDate }"/></td>
                                 <td><c:out value="${ issue.progressStatus }"/></td>
                                 <td><c:out value="${ issue.importance }"/></td>
                                 <td><c:out value="${ issue.register.name }"/></td>
                                 <td><c:out value="${ issue.manager.name }"/></td>
                                 <td><button class="btn btn-outline-dark" data-toggle="modal" data-target="#myModal" style="width:45pt;height:25pt; vertical-align: middle;">배정</button></td>
                             </tr>
                             <input type="hidden" name="projectNo" value="${ issue.projectNo }">
							</c:forEach>

                         </tbody>
                         <tfoot></tfoot>
                     </table>

                 </div>
             </div>
         </div>
     </main>

<jsp:include page="../common/footer.jsp"/>
<script>
 $("#registModal").click(function(){

		var projectNo = ${ requestScope.projectNo };
		
		console.log(projectNo);
		
		$("#projectNo").val(projectNo);

		console.log("프로젝트 번호는 : " + projectNo);	
}); 

</script>
</body>
</html>