<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 조회 모달 -->
<div class="modal" id="myModal">
    <div class="modal-dialog modal-xl modal-dialog modal-dialog-scrollable">
    <div class="modal-content">

        <!-- Modal Header -->
        <div class="modal-header">
        <h4 class="modal-title">이슈 수정</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>

        <!-- Modal body -->
                   <div class="modal-body">

                        <div class="mt-4 row">
                            <div class="col-2 center"><label>이슈명</label></div>
                            <div class="col"><input type="text" name="name"></div>
                            <div class="col"></div>
                            <div class="col-2 center"><label>상태</label></div>
                            <div class="col-4">
                                <select class="importance ms-auto" name="progressStatus">
                                    <option value="대기중">대기중</option>
                                    <option value="처리중">확인중</option>
                                    <option value="완료">완료</option>
                                </select>
                            </div>
                        </div>

                       <div class="mt-4 row">
                       		<div class="col-2 center"><label for="start-date">등록일</label></div>
                            <div class="col"><input type="date" id="start-date" name="createdDate"></div>
                       
                            <div class="col-2 center"><label for="start-date">처리일</label></div>
                            <div class="col-4"><input type="date" id="start-date" name="deadline"></div>
                        </div>

                        <div class="mt-4 row">
                            <div class="col-2 center"><label>이슈 제기자</label></div>
                            <div class="col">
                            	<select id="register" name="register">
									<option value="" selected disabled>제기자 선택</option>
									<c:forEach var="issue" items="${ issueList }">
										<option value="${ issue.registerNo }"><c:out value="${ issue.register.name }"/></option>
									</c:forEach>
								</select>
                            </div>
                            
                            <div class="col-2 center"><label>이슈 담당자</label></div>
                            <div class="col">
                            	<select id="register" name="register">
									<option value="" selected disabled>담당자 선택</option>
									<c:forEach var="issue" items="${ issueList }">
										<option value="${ issue.managerNo }"><c:out value="${ issue.manager.name }"/></option>
									</c:forEach>
								</select>
                            </div>
                        </div>

                        <div class="mt-4 row">
                            <div class="col-2 center"><label>중요도</label></div>
                            <div class="col-4">
                            <select class="importance ms-auto" name="importance">
                                <option value="낮음">낮음</option>
                                <option value="보통">보통</option>
                                <option value="긴급">긴급</option>
                            </select>
                            </div>
                        </div>

                        <div class="mt-4 row">
                            <div class="col-2 center" style="vertical-align: top;"><label>이슈내용</label></div>
                <div class="col"><textarea name="" id="" cols="22" rows="auto" name="content"></textarea></div>
                <div class="col-1"></div>
                <div class="col-2 center" style="vertical-align: top;"><label>처리내용</label></div>
                <div class="col-4"><textarea name="" id="" cols="22" rows="auto" name="content"></textarea></div>
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

<script>
$(function() {
	   const $tds = document.querySelectorAll("#listArea td");   /* 이벤트 클릭 했을 때의 this  */
	   console.log($tds);
	   for (let i = 0; i < $tds.length; i++) {
	      $tds[i].onclick = function() {
	         const no = this.parentNode.children[0].innerText;
	         const ex = this.parentNode; // this는 td의 부모인 tr
	         console.log(no);
	         
	          $.ajax({
	            url :"issueDetail",
	            type : "get",
	            data : { no : no },
	            success : function(data, textStatus, xhr) {
	               
	                  console.log(data);
	                  console.log(Object.entries(data));
	                  
	                  const guideArray = Object.entries(data);
	                  
	                  console.log(guideArray[3][1]);
	                  const $fileNo = guideArray[13][1];
	                  console.log($fileNo);
	                  
	                  $("#read-no").val(guideArray[0][1]);      
	                  $("#read-title").val(guideArray[2][1]);
	                  $("#read-content").val(guideArray[3][1]);
	                  $("#read-writerNo").val(guideArray[8][1]);
	                  $("#read-originalName").val(guideArray[14][1]);
	                  $("#readModal").modal("show");
	                  ex.children[2].innerText=guideArray[9][1];      //ex가 tr이고 행 전체의 2번 인덱스에 guideArray 9번째 배열의 1번 인덱스
	                  
	                  if($fileNo != null) {
	                     
	                     const $downloadTag = "<a href='${pageContext.servletContext.contextPath}/guide/download/" + $fileNo 
	                                           + "' class='dropdown-item' id='downloadguide'>다운로드</a>";
	               		 const $deleteTag = "<a href='${pageContext.servletContext.contextPath}/guide/deleteFile/" + $fileNo 
	                     + "' class='dropdown-item' id='downloadguide'>삭제</a>";                                 
	                     
	                     $("#downloadarea").empty();
	                     $("#downloadarea").append($downloadTag);
	                     $("#downloadarea").append($deleteTag);
//	                      $("#downloadguide").href("${pageContext.servletContext.contextPath}/guide/download/" + $fileNo);
	                  }
	              }, 
	              error:function(data) {
	                  console.log(data);
	               }
	          });
	         }
	   }
	});
</script>
</body>
</html>