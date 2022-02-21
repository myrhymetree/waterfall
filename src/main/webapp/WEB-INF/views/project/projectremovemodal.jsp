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
  width: 510px;
  height: 600px;
  padding: 30px;
}

#title-write {
  width: 390px;
}
.my-modal-body {
  margin-left: 0px;
}
.my-textarea-div {
  width: 440px;
  height: 330px;
}
#my-textarea {
  display: block;
  width: 100%;
  height: 100%;
}
.my-modal-upload {
	align: left;
}


.my-read-textarea-div {
  width: 440px;
  height: 245px;
}

#read-title {
	width: 390px;
}
#read-content {
	width: 436px;
}
</style>
</head>
<body>
	<div class="modal fade" id="projectRemove" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
	    	<div class="modal-content" style="top: 172px">
	        	<form action="${ pageContext.servletContext.contextPath }/meeting/regist" method="post" encType="multipart/form-data">
	            	<div class="my-modal-header mb-4">
	                	<label class="me-4" for="title-write">제목</label>
	                    <input type="text" id="title-write" name="title">
					</div>
                	<div class="my-modal-body">
	                	<div class="my-textarea-div mb-3">
	                    	<textarea id="my-textarea" cols="30" rows="10" name="content"></textarea>
	           			</div>
	            	</div>
	            	<div class="my-modal-upload mb-4">
	            		<input id="meeting-fileupload"  type="file" name="meetingfile"  multiple>
	            	</div>
					<div class="mt-4 my-modal-footer-read" style="text-align: left">
	                	<input type="hidden" name="no" value="${ meeting.member.memberName }">
<!-- 	                	<button type="submit" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#subModal">등록</button> -->
	                	<button type="submit" class="btn btn-secondary" >등록</button>
                      	<input type="button" id="backToList" class="btn btn-secondary" data-bs-dismiss="modal" value="취소">
                      	
                  	</div>
				</form>
			</div>
		</div>
	</div>

<script>

	$("#backToList").click( function() {
		console.log("123123");
		$("#title-write").val("");
		$("#my-textarea").val("");
		$("#meeting-fileupload").val("");
	});
	$("#delete").click( function() {
		location.href= "${ pageContext.servletContext.contextPath }/meeting/remove/" + $("#read-no").val();
	});
</script>
</body>
</html>



























