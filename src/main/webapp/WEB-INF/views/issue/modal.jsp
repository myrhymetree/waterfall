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
                           <div class="col-2 center"><label>업무명</label></div>
                           <div class="col">
                           <select id="dept" name="dept">
						<option value="" selected disabled>
						부서 선택
						</option>
		<%-- 										<c:forEach var="dept" items="${ deptList }">
													<option value="${ dept.deptCode }">
														<c:out value="${ dept.deptName }"/>
													</option>
												</c:forEach> --%>
						</select>

                            </div>
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

<!-- 등록 모달 encType="multipart/form-data"-->
<div class="modal" id="registModal">
    <form id="registForm" onsubmit="test()">
    <div class="modal-dialog modal-xl modal-dialog modal-dialog-scrollable">
    <div class="modal-content">

        <!-- Modal Header -->
        <div class="modal-header">
        <h4 class="modal-title">이슈 등록</h4>
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
                       	   <div class="col-2 center"><label for="start-date">시작일</label></div>
                           <div class="col-4"><input type="date" id="start-date" name="createdDate"></div>
                       
                           <div class="col-2 center"><label for="start-date">마감일</label></div>
                           <div class="col"><input type="date" id="start-date" name="deadline"></div>
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
                           <select class="importance ms-auto" name="importance">
                               <option value="낮음">낮음</option>
                               <option value="보통">보통</option>
                               <option value="긴급">긴급</option>
                           </select>
                           </div>
                       </div>

                       <div class="mt-4 row">
                           <div class="col-2 center" style="vertical-align: top;"><label>이슈내용</label></div>
                		   <div class="col"><textarea name="content" id="" cols="100" rows="auto" name="content"></textarea></div>
            		  </div>

            <div class="mt-4 row">
                <div class="col-2 center" style="vertical-align: top;"><label>첨부파일</label></div>
                <!-- <div class="col-10"><input type="file"  name="multiFiles" multiple></div> -->
            </div>
        </div>

        <!-- Modal footer -->
        <div class="modal-footer row">
                <div class="col-5">
                    <button type="submit" onclick="submit" id="registSubmit" class="btn btn-outline-dark" data-toggle="modal" data-target="#mySubModal">확인</button>
                </div>
                <div class="col-4">
                    <button type="button" class="btn btn-outline-dark" data-dismiss="modal">취소</button>
                </div>
        </div>
    </div>
    </div>
	</form>
</div>

<!-- <!-- 모달 확인 버튼 누를 시 나오는 모달 -->
<div class="modal" id="mySubModal">
    <div class="modal-dialog">
    <div class="modal-content">

        Modal Header
        <div class="modal-header">
        <h4 class="modal-title">이슈</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>

        Modal body
        <div class="modal-body">
        정보를 저장하시겠습니까?
        </div>

        Modal footer
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

 /* 쿼리스트링 형태의 taskNo를 파라미터에서 추출해주는 함수와 form에 있는 data를 submit 했을 때 발생하는 이벤트 */
/*  location.search
 function getParameterByName(name) { name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), 
	results = regex.exec(location.search); return results == null ? "" : 
	decodeURIComponent(results[1].replace(/\+/g, " ")); 
	}

var taskNo = getParameterByName('taskNo');

console.log("업무번호는 : " + taskNo)  */


  function test(e) { 
	
	const $taskNo = ${ requestScope.taskNo  }
	console.log($taskNo);

	 console.log("호출됨"); 
	 var form = document.forms["registForm"];
	 console.log(form);
	 /* form.action = "${ pageContext.servletContext.contextPath }/issue/regist/" + taskNo; */
	 form.action = "${ pageContext.servletContext.contextPath }/issue/regist/" + $taskNo;
	 form.method="POST";	 
 }
/* 쿼리스트링 형태의 taskNo를 파라미터에서 추출해주는 함수와 form에 있는 data를 submit 했을 때 발생하는 이벤트 */
	
/* 	$("#save-pm").on("click", function() {
		console.log($("#member option:selected").val());
		console.log($("#member option:selected").text());
		if("사원 선택" != $("#member option:selected").text()) {
			$("#pm-name-area").val($("#member option:selected").text());
			$("#pmNumber").val($("#member option:selected").val());
			console.log($("#pmNumber").val());
		}

	}); */
/* 	$("#task").on("change", function() {
		const taskNo = $(this).val();
		console.log(taskNo);
		const url = "${pageContext.servletContext.contextPath}/issue/regist/task/" + $(this).val();
		/* $("#team option").remove();
		console.log(taskNo)
		$.ajax({
			url: url,
			type: "get",
			data: { taskNo : taskNo },
			success: function(data) {
				console.table(JSON.parse(data.taskList));
 				const $teamPreOption = "<option value='' selected disabled>팀 선택</option>";
				$("#team").append($teamPreOption);
				const $memberPreOption = "<option value='' selected disabled>사원 선택</option>";
				$("#member").append($memberPreOption);
				taskList = JSON.parse(data.taskList);
				for(let i = 0; i < taskList.length; i++) {
					const $taskTag = "<option value = '" + taskList[i].taskNo + "'>" + teamList[i].task.taskName + "</option>" 
					$("#task").append($taskTag);
				}
			},
			error: function(data) {
				console.log("ajax통신 실패 에러");
			}
		});
	}); */
	
	
/* 	$("#team").on("change", function() {
		const teamCode = $(this).val();
		console.log(teamCode);
		const url = "${pageContext.servletContext.contextPath}/project/regist/member/" + $(this).val();
		$("#member option").remove();
				const $memberPreOption = "<option value='' selected disabled>사원 선택</option>";
				$("#member").append($memberPreOption);
		$.ajax({
			url: url,
			type: "get",
			data: {teamCode:teamCode},
			success: function(data) {
				
				memberList = JSON.parse(data.memberList);
				for(let i = 0; i < memberList.length; i++) {
					const $memberTag = "<option value = '" + memberList[i].no + "'>" + memberList[i].name + "</option>"
					console.log($memberTag);
						
					$("#member").append($memberTag);
				}
			},
			error: function(data) {
				console.log("ajax통신 실패 에러");
			}
		});
	}); */
	
/* 	$(".readonly").keydown(function(e){
        e.preventDefault();
    }); */
	
</script>               

</body>
</html>