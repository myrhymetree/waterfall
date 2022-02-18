<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style>

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
  width: 420px;
}
.my-modal-body {
  margin-left: 0px;
}
.my-textarea-div {
  width: 440px;
  height: 430px;
}
#my-textarea {
  display: block;
  width: 100%;
  height: 100%;
}


</style>





</head>
<body>

		<div class="modal fade" id="writeModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
                <div class="modal-content" style="top: 172px">
                    <form action="meeting/regist" method="post">
                        <div class="my-modal-header mb-4">
                            <label class="me-2" for="title-write">제목</label>
                            <input type="text" id="title-write">
                        </div>
                        <div class="my-modal-body">
                            <div class="my-textarea-div mb-3">
                                <textarea name="my-textarea" id="my-textarea" cols="30" rows="10"></textarea>
                            </div>
                            <div class="my-modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#subModal">등록</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                            </div>
                        </div>    
                    </form>
                </div>
            </div>
        </div>
        <!-- Modal HTML  -->

		<!-- 게시글 조회 모달 -->
	     <div class="modal fade" id="#readModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	         <div class="modal-dialog">
	             <!--  style="top: 200px" 모달 위치변경은 top,left이런거로 조정하면 돼요 -->
	             <div class="modal-content" style="top: 172px">
	                 <form>
	                     <div class="my-modal-header mb-4">
	                         <label class="me-2" for="title-write">제목</label>
	                         <input type="text" id="title-write" value="11">
	                     </div>
	                     <div class="my-modal-body">
	                         <div class="my-textarea-div mb-3">
	                             <textarea name="my-textarea" id="my-textarea" cols="30" rows="10">게시글 내용입니다.</textarea>
	                         </div>
	                     </div>
	                     <div class="my-modal-footer-read">
	                         <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">돌아가기</button>
	                     </div>
	                 </form>
	             </div>
	         </div>
	     </div>
        <!-- subModal -->
        <div class="modal fade" id="subModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" style="left: 150px; top: 300px; width: 300px; height: 150px; margin: 0; padding: 0;">
                    <div class="modal-body align-middle my-modal-message">
                        등록되었습니다.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">확인</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- //subModal -->




</body>
</html>



























