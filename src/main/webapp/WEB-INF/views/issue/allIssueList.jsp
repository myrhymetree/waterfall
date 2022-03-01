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
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" data-auto-replace-svg="nest"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
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
	td { vertical-align: middle !important; }
</style>
</head>
<body>
<jsp:include page="../common/inprojectheader.jsp"/>
     <main>
         <div class="container-fluid px-4">
             <br>
             <br>
             <h1 class="mt-4"><i class="fas fa-exclamation-circle"></i>이슈관리</h1>
             <div class="card mb-4">
                 <div class="card-header">
                     <i class="fas fa-table me-1"></i>
                     모든 이슈 목록
                 </div>
                 <div class="card-body">
                     <table id="datatablesSimple">
                        <colgroup>
                             <col style="width:10%"/>
                             <col style="width:20%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                             <col style="width:10%"/>
                         </colgroup>
                         <thead>
                             <tr>
                                 <th>No</th>
                                 <th>업무 명</th>
                                 <th>이슈 명</th>
                                 <th>발생일</th>
                                 <th>상태</th>
                                 <th>중요도</th>
                                 <th>이슈제기자</th>
                                 <th>이슈담당자</th>
                                 <th>이슈배정</th>
                             </tr>
                         </thead>
                         <tbody>
                         	<c:forEach var="issue" items="${ requestScope.allIssueList }" varStatus="status">
                             <tr>
                                 <td><c:out value="${ issue.no }"/></td>
                                 <td><c:out value="${ issue.taskCode.taskCategoryName }"/></td>
                                 <td>업무명 오타</td>
                                 <td><c:out value="${ issue.createdDate }"/></td>
                                 <td><c:out value="${ issue.progressStatus }"/></td>
                                 <td><c:out value="${ issue.importance }"/></td>
                                 <td><c:out value="${ issue.register.name }"/></td>
                                 <td><c:out value="${ issue.manager.name }"/></td>
                                 <td><button class="btn btn-outline-dark" data-toggle="modal" data-target="#myModal">배정</button></td>
                             </tr>
							</c:forEach>

                         </tbody>
                         <tfoot></tfoot>
                     </table>

                     <!-- The Modal -->
                     <div class="modal" id="myModal">
                         <div class="modal-dialog modal-xl modal-dialog modal-dialog-scrollable">
                         <div class="modal-content">

                             <!-- Modal Header -->
                             <div class="modal-header">
                             <h4 class="modal-title">이슈 배정</h4>
                             <button type="button" class="close" data-dismiss="modal">&times;</button>
                             </div>

                             <!-- Modal body -->
                             <div class="modal-body">
                                 <div class="mt-4 row">
                                     <div class="col-2 center"><label>업무명</label></div>
                                     <div class="col"><input type="text" readonly></div>
                                     <div class="col-2 center"><label for="start-date">처리일</label></div>
                                     <div class="col-4"><input type="date" id="start-date" name="start-date"></div>
                                 </div>

                                 <div class="mt-4 row">
                                     <div class="col-2 center"><label>이슈명</label></div>
                                     <div class="col"><input type="text" readonly></div>
                                     <div class="col"></div>
                                     <div class="col-2 center"><label>상태</label></div>
                                     <div class="col-4">
                                         <select class="importance ms-auto">
                                             <option value="낮음">대기중</option>
                                             <option value="중간">확인중</option>
                                             <option value="높음">처리중</option>
                                             <option value="매우높음">완료</option>
                                         </select>
                                     </div>
                                 </div>

                                 <div class="mt-4 row">
                                     <div class="col-2 center"><label for="start-date">등록일</label></div>
                                     <div class="col"><input type="date" id="start-date" name="start-date"></div>
                                 </div>

                                 <div class="mt-4 row">
                                     <div class="col-2 center"><label>이슈 제기자</label></div>
                                     <div class="col"><input type="text" readonly></div>
                                     <div class="col-2 center"><label>이슈 담당자</label></div>
                                     <div class="col"><input type="text" readonly></div>
                                 </div>

                                 <div class="mt-4 row">
                                     <div class="col-2 center"><label>중요도</label></div>
                                     <div class="col-4">
                                     <select class="importance ms-auto">
                                         <option value="낮음">낮음</option>
                                         <option value="중간">중간</option>
                                         <option value="높음">높음</option>
                                         <option value="매우높음">매우높음</option>
                                     </select>
                                     </div>
                                 </div>

                                 <div class="mt-4 row">
                                     <div class="col-2 center" style="vertical-align: top;"><label>이슈내용</label></div>
                                     <div class="col"><textarea name="" id="" cols="22" rows="auto" readonly></textarea></div>
                                     <div class="col-1"></div>
                                     <div class="col-2 center" style="vertical-align: top;"><label>처리내용</label></div>
                                     <div class="col-4"><textarea name="" id="" cols="22" rows="auto" readonly></textarea></div>
                                 </div>

                                 <div class="mt-4 row">
                                     <div class="col-2 center" style="vertical-align: top;"><label>첨부파일</label></div>
                                     <div class="col-3">
                                         <div class="btn-group">
                                             <button type="button" class="btn btn-outline-dark">첨부파일.word</button>
                                             <button type="button" class="btn btn-outline-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                                               <span class="caret"></span>
                                             </button>
                                             <div class="dropdown-menu">
                                               <a class="dropdown-item" href="#">다운로드</a>
                                               <a class="dropdown-item" href="#">삭제</a>
                                             </div>
                                           </div>
                                     </div>
                                     <div class="col-1"></div>
                                     <div class="col-2 center" style="vertical-align: top;"><label>첨부파일</label></div>
                                     <div class="col-3">
                                         <div class="btn-group">
                                             <button type="button" class="btn btn-outline-dark">첨부파일.word</button>
                                             <button type="button" class="btn btn-outline-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                                               <span class="caret"></span>
                                             </button>
                                             <div class="dropdown-menu">
                                               <a class="dropdown-item" href="#">다운로드</a>
                                               <a class="dropdown-item" href="#">삭제</a>
                                             </div>
                                         </div>
                                   </div>
                             </div>

                             </div>

                             <!-- Modal footer -->
                             <div class="modal-footer row">
                                     <div class="col-5">
                                         <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#mySubModal">확인</button>
                                     </div>
                                     <div class="col-4">
                                         <button type="button" class="btn btn-outline-dark" data-dismiss="modal">취소</button>
                                     </div>
                             </div>
                         </div>
                         </div>
                     </div>

                     <!-- 모달 확인 버튼 누를 시 나오는 모달 -->
                     <div class="modal" id="mySubModal">
                         <div class="modal-dialog">
                         <div class="modal-content">
                     
                             <!-- Modal Header -->
                             <div class="modal-header">
                             <h4 class="modal-title">이슈</h4>
                             <button type="button" class="close" data-dismiss="modal">&times;</button>
                             </div>
                     
                             <!-- Modal body -->
                             <div class="modal-body">
                             정보를 저장하시겠습니까?
                             </div>
                     
                             <!-- Modal footer -->
                             <div class="modal-footer row">
                                 <div class="col-5">
                                 <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#mySubModal">확인</button>
                                 </div>
                                 <div class="col-4">
                                 <button type="button" class="btn btn-outline-dark" data-dismiss="modal" style="text-align: left;">취소</button>
                                 </div>
                             </div>
                     
                         </div>
                         </div>
                     </div>

                 </div>
             </div>
         </div>
     </main>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>