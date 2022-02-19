<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>To Do 게시판</title>
<style>

#layoutSidenav_content .todo h2 {
  height: 50px;
  line-height: 1.5;
  padding-left: 60px;
}
#layoutSidenav_content .todo h2 span {
  font-size: 1rem;
  vertical-align: bottom;
}
#layoutSidenav_content .todo hr {
  width: 95%;
  margin: 10px auto;
}
#layoutSidenav_content .todo .tbl-wrapper {
  width: 95%;
  /* font-size: 1rem; */
}
#layoutSidenav_content .todo .tbl-wrapper .write button {
  /* font-size: 1rem; */
  background: #000;
  padding: 4px 18px;
  border-radius: 8px;
}
#layoutSidenav_content .todo .tbl-wrapper .paging {
  text-align: center;
}
#layoutSidenav_content .todo .tbl-wrapper .paging button {
  /* font-size: 1rem; */
  background: none;
  border: none;
}
#layoutSidenav_content .todo .tbl-wrapper .paging button:hover {
  text-decoration: underline;
}
table, th, td {
  border: 1px solid #e5e5e5;
  /* border-collapse: collapse; */
}
#layoutSidenav_content .todo .todo-tbl {
  width: 100%;
  /* height: 400px; */ /* 페이지 처리 시 이슈 발생 */
  text-align: center;
  border-left: none;
  border-right: none;
}
#layoutSidenav_content .todo .todo-tbl tbody tr td/* :first-child, 
#layoutSidenav_content .todo .todo-tbl tbody tr td:last-child */ {
  border-left: none;
  border-right: none;
}
#layoutSidenav_content .todo .todo-tbl thead tr {
  background: #f0f0f0;
}
#layoutSidenav_content .todo .todo-tbl thead tr th/* :first-child, 
#layoutSidenav_content .todo .todo-tbl thead tr th:last-child */ {
  border-left: none;
  border-right: none;
  border-bottom-color: #999;
}
#layoutSidenav_content .todo .todo-tbl tr {
  height: 30px;
}
#layoutSidenav_content .todo .todo-tbl tbody tr:hover {
  background: paleturquoise;
}
#layoutSidenav_content .todo .tbl-wrapper .search-area .search-set {
  text-align: center;
}
#layoutSidenav_content .todo .tbl-wrapper .search-area .search-set #search-input {
  width: 300px;
}
#layoutSidenav_content .todo .tbl-wrapper .search-area .search-set button {
  background: none;
  border: none;
}

.my-modal-footer-read {
  text-align: center;
}
.my-modal-footer-read button {
  color: #000;
  background: none;
  padding: 5px 25px;
}

.modal-content {
  width: 635px;
  height: 600px;
  padding: 30px;
}
#title-write {
  width: 480px;
}
.my-modal-body {
  margin-left: 47px;
}
.my-textarea-div {
  width: 480px;
  height: 430px;
}
#my-textarea {
  display: block;
  width: 100%;
  height: 100%;
}
.my-modal-footer button {
  color: #000;
  background: none;
  padding: 5px 25px;
}
.my-modal-footer button:first-child {
  margin-right: 306px;
}


.my-modal-message {
  line-height: 45px;
}

</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>

	<div class="modal fade" id="writeModal" data-bs-backdrop="static"
		tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
			<div class="modal-content" style="top: 172px">
				<form>
					<div class="my-modal-header mb-4">
						<label class="me-2" for="title-write">제목</label> <input
							type="text" id="title-write">
					</div>
					<div class="my-modal-body">
						<div class="my-textarea-div mb-3">
							<textarea name="my-textarea" id="my-textarea" cols="30" rows="10"></textarea>
						</div>
						<div class="my-modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-toggle="modal" data-bs-target="#subModal">등록</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">취소</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Modal HTML  -->

	<!-- subModal -->
	<div class="modal fade" id="subModal" data-bs-backdrop="static"
		tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"
				style="left: 150px; top: 300px; width: 300px; height: 150px; margin: 0; padding: 0;">
				<div class="modal-body align-middle my-modal-message">
					등록되었습니다.</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>
	<!-- //subModal -->

	<!-- 게시글 조회 모달 -->
	<div class="modal fade" id="readModal${ todo.no }" data-bs-backdrop="static"
		tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
			<div class="modal-content" style="top: 172px">
				<form>
					<div class="my-modal-header mb-4">
						<label class="me-2" for="title-write">제목</label> <input
							type="text" id="title-write" value="${ todo.title }">
					</div>
					<div class="my-modal-body">
						<div class="my-textarea-div mb-3">
							<textarea name="my-textarea" id="my-textarea" cols="30" rows="10"><c:out value="${ todo.writer.name }" /></textarea>
						</div>
					</div>
					<div class="my-modal-footer-read">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">돌아가기</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="todo">
		<h2>
			<span><i class="far fa-clipboard me-1"></i>To Do 게시판</span>
		</h2>
		<hr>
		<div class="tbl-wrapper mx-auto">
			<div class="write">
				<button type="button" class="btn btn-secondary mb-2"
					data-bs-toggle="modal" data-bs-target="#writeModal">
					<i class="far fa-edit me-1"></i>등록
				</button>
			</div>
			<table class="todo-tbl">
				<colgroup>
					<col width="10%">
					<col width="55%">
					<col width="10%">
					<col width="15%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th scope="colgroup">글번호</th>
						<th scope="colgroup">제목</th>
						<th scope="colgroup">조회수</th>
						<th scope="colgroup">등록일</th>
						<th scope="colgroup">등록자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="todo" items="${ requestScope.todoList }">
						<tr data-bs-toggle="modal" data-bs-target="#readModal${ todo.no }">
							<td><c:out value="${ todo.no }" /></td>
							<td><c:out value="${ todo.title }" /></td>
							<td><c:out value="${ todo.count }" /></td>
							<td><c:out value="${ todo.postingDate }" /></td>
							<td><c:out value="${ todo.writer.name }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<!-- <div class="paging mt-3">
				<button>
					<i class="fas fa-angle-double-left"></i>
				</button>
				<button>
					<i class="fas fa-angle-left"></i>
				</button>
				<button>1</button>
				<button>2</button>
				<button>3</button>
				<button>4</button>
				<button>5</button>
				<button>
					<i class="fas fa-angle-right"></i>
				</button>
				<button>
					<i class="fas fa-angle-double-right"></i>
				</button>
			</div> -->
			
			<!-- 페이지 처리 -->
			<jsp:include page="../../common/todoPaging.jsp" />

			<!-- 검색 폼 -->
			<div class="search-area">
				<div class="search-set mt-2">
					<!-- <select name="searchcat" id="searchcat">
						<option value="제목">제목</option>
						<option value="내용">내용</option>
					</select> <input type="search" id="search-input"> -->
					<form id="loginForm"
						action="${ pageContext.servletContext.contextPath }/todo/list"
						method="get" style="display: inline-block">
						<input type="hidden" name="currentPage" value="1"> <!-- 검색 드롭 창 --><select
						id="searchCondition" name="searchCondition">
						<option value="title"
							${ requestScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
						<option value="content"
							${ requestScope.selectCriteria.searchCondition eq "content"? "selected": "" }>내용</option>
						<option value="writer"
							${ requestScope.selectCriteria.searchCondition eq "writer"? "selected": "" }>등록자</option>
						</select> <!-- 검색 입력 창 --><input type="search" id="searchValue" name="searchValue"
						value="<c:out value="${ requestScope.selectCriteria.searchValue }"/>">
						<!-- 찾기 버튼 -->
						<button type="submit">
							<i class="fas fa-search"></i>
						</button>
					</form>
				</div>
			</div>
			<!-- 검색 폼 끝 -->
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>