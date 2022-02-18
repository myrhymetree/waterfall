<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
/*   height: 400px; */
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

th {
    text-align: inherit;
    text-align: -webkit-match-parent;
    
    height: 50px;
}
td {
    height: 50px;
}

</style>





</head>
<body>
        

        





	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>
	<jsp:include page="/WEB-INF/views/board/meeting/modal.jsp"/>

   
   <div class="todo">
                    <h2><span><i class="far fa-clipboard me-1"></i>회의록 게시판</span></h2>
                    <hr>
                    <div class="tbl-wrapper mx-auto">
                        <div class="write">
                            <button type="button" class="btn btn-secondary mb-2" data-bs-toggle="modal" data-bs-target="#writeModal"><i class="far fa-edit me-1"></i>등록</button>
                        </div>
                        <table class="todo-tbl" id="meetingTable">
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
                            <c:forEach var="meeting" items="${ meetingList }">
                            	<tr class="mt-2" data-bs-toggle="modal" data-bs-target="#readModal${ meeting.no }">  <!-- class="mt-2" data-bs-toggle="modal" data-bs-target="#readModal" -->
                            		<td><c:out value="${ meeting.no }" /></td>
                            		<td><c:out value="${ meeting.title }" /></td>
                            		<td><c:out value="${ meeting.boardCount }" /></td>
                            		<td><c:out value="${ meeting.updatedDate }" /></td>
                            		<td><c:out value="${ meeting.member.memberName }" /></td>
                            	</tr>
                            	<!-- 게시글 조회 모달 -->
						        <div class="modal fade" id="readModal${ meeting.no }" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						            <div class="modal-dialog">
						                <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
						                <div class="modal-content" style="top: 172px">
						                    <form>
						                        <div class="my-modal-header mb-1">
						                            <label class="me-2" for="title-write">제목</label>
						                            <input type="text" id="title-write" value="${ meeting.title}" style="width: 40%" readonly>
						                        </div>
						                        <div class="my-modal-header mb-2">
						                            <label class="me-2">작성자</label>
						                            <label class="me-2"><c:out value="${ meeting.member.memberName }" /></label>
						                        </div>
						                        <div class="my-modal-body mt-1">
						                            <div class="my-textarea-div mb-2">
						                                <textarea readonly name="my-textarea" id="my-textarea" cols="40" rows="10"><c:out value="${ meeting.content }" /></textarea>
						                            </div>
						                        </div>
						                        <div class="my-modal-footer-read">
						                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">돌아가기</button>
						                        </div>
						                    </form>
						                </div>
						            </div>
						        </div>
                            </c:forEach>
                            </tbody>
                        </table>
						<jsp:include page="/WEB-INF/views/common/sungwonpaging.jsp"/>	
                        
                        
                        
                       
                       
                        
                        
                        
                        <div class="search-area">
                            <div class="search-set mt-2">
	                            <form action="${ pageContext.servletContext.contextPath }/meeting/list" >
									<select id="searchCondition" name="searchCondition">
										<option value="writer" ${ requestScope.selectCriteria.searchCondition eq "writer"? "selected": "" }>작성자</option>
										<option value="title" ${ requestScope.selectCriteria.searchCondition eq "title"? "selected": "" }>제목</option>
										<option value="content" ${ requestScope.selectCriteria.searchCondition eq "content"? "selected": "" }>내용</option>
									</select> <input type="search" id="searchValue" name="searchValue">
									<button class="btn btn-bs" type="submit"><i class="fas fa-search"></i></button>
								</form>
                            </div>
                        </div>
                    </div>
                </div>
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
	
	
	<!-- <script>
		if(document.querySelectorAll("#meetingTable")){
			const $tds = document.querySelectorAll("#meetingTable td");
			for(let i = 0; i < $tds.length; i++) {
				
				$tds[i].onclick = function() {
					const no = this.parentNode.children[0].innerText;
					location.href = "${ pageContext.servletContext.contextPath }/meeting/detail/" + no;
				}
				$tds[i].onmouseenter = function() {
					this.parentNode.style.cursor = "pointer";
				}
				
			}
		}
			
	</script> -->
	<script>
// 	$("button[name='modify']").click(function(){
// 		action='modify';
// 		type = 'PUT';
// 		bno = this.value;

// 		// content 담기
// 		var row = $(this).parent().parent().parent();
// 		var tr = row.children();
		
// 		var userName = tr.eq(2).text();
// 		var contents = tr.eq(1).text();

// 		$("#modal-title").text("수정하기");

// 		$("#userName").val(userName);
// 		$("#contents").val(contents);
		
// 		$("#myModal").modal();
// 	});
	
// 	if(document.querySelectorAll("#meetingTable")){
// 		const $tds = document.querySelectorAll("#meetingTable td");
// 		for(let i = 0; i < $tds.length; i++) {
			
// 			$tds[i].onclick = function() {
// 				const data = this.parentNode.children[1].innerText;
// 				$('#readModal').modal('show'); 
// 				console.log(data);
// 				document.getElementById("title-write").innerHtml = data;
// 				document.getElementById("test").innerHtml = 123;				

// 			}
// 			$tds[i].onmouseenter = function() {
// 				this.parentNode.style.cursor = "pointer";
// 			}
			
// 		}
// 	}
	</script>
</body>
</html>



























