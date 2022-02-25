<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
    width: 700px;
    height: 670px;
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

.cklabel {
	font-size: 1.2em;
	style: bole;
}
.check {
	zoom: 2;
}

</style>
</head>
<body>
	<div class="modal fade" id="member-regist-modal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
	    	<div class="modal-content" style="top: 172px">
	        	<form action="${ pageContext.servletContext.contextPath }/meeting/regist" method="post" encType="multipart/form-data">
					<div class="mt-4 mb-4"></div>
	           		<div class="my-modal-body">
		            	<div class="row">
							<div class="col">
							    <label style="margin-left: 1%; font-size: 2em"><i class="fas fa-users-cog"></i>인원배정</label>
							</div>
                        </div>
						<div class="mt-4">
							<span  style="margin-left: 2%">
								<select id="dept" name="dept">
									<option value="" selected disabled>부서 선택</option>
									<c:forEach var="dept" items="${ allDept }">
										<option value="${ dept.deptCode }"><c:out value="${ dept.deptName }"/></option>
									</c:forEach>
								</select>
							</span>
							<span>
								<select id="team" name="team">
									<option value="" selected disabled>팀 선택</option>
								</select>
							</span>
							<span>
								<select id="member" name="member">
									<option value="" selected disabled>사원 선택</option>
								</select>
							</span>
							<span>
							    <input id="save-member" type="button" value="등록">
							</span>
						</div>
						<div>
							<label class="cklabel" style="margin: 2em"><input type="checkbox" class="check" value="DBA1" id="allCheck">전체 선택/해제</label><br>
							<label class="cklabel" style="margin: 2em"><input type="checkbox" class="check checkbok" value="DBA1">DBA1</label>
							<label class="cklabel" style="margin: 2em"><input type="checkbox" class="check checkbok" value="DBA1">DBA1</label>
							<label class="cklabel" style="margin: 2em"><input type="checkbox" class="check checkbok" value="DBA1">DBA1</label>
							<label class="cklabel" style="margin: 2em"><input type="checkbox" class="check checkbok" value="DBA1">DBA1</label>
							<label class="cklabel" style="margin: 2em"><input type="checkbox" class="check checkbok" value="DBA5">DBA5</label>
							<label class="cklabel" style="margin: 2em"><input type="checkbox" class="check checkbok" value="DBA6">DBA6</label>
							<label class="cklabel" style="margin: 2em"><input type="checkbox" class="check checkbok" value="DBA7">DBA7</label>
							<label class="cklabel" style="margin: 2em"><input type="checkbox" class="check checkbok" value="DBA8">DBA8</label>
                    	</div>
                     	<div class="row mt-3">
							<div style="text-align: left; margin-left: 8%;">
							     <label id="multiPrint"></label>
							</div>
		            	</div>
	            	</div>
	            	<input type="hidden" name="memberNo" id="memberNo">
	            	<input type="hidden" name="managerNo" id="managerNo">
				</form>
			</div>
		</div>
	</div>
	<script>
	
	$("#save-member").on("click", function() {
		console.log($("#member option:selected").val());
		console.log($("#member option:selected").text());
		if("사원 선택" != $("#member option:selected").text()) {
			const $managerNo = ${ sessionScope.loginMember.no};
			$("#memberNo").val($("#member option:selected").val());
			$("#managerNo").val($managerNo);
			console.log($("#memberNo").val());
			console.log($("#managerNo").val());
		}

	});
	
	$("#dept").on("change", function() {
		const deptCode = $(this).val();
		const url = "${pageContext.servletContext.contextPath}/project/regist/team/" + $(this).val();
		$("#team option").remove();
		$.ajax({
			url: url,
			type: "get",
			data: {deptCode:deptCode},
			success: function(data) {
				const $teamPreOption = "<option value='' selected disabled>팀 선택</option>";
				$("#team").append($teamPreOption);
				const $memberPreOption = "<option value='' selected disabled>사원 선택</option>";
				$("#member").append($memberPreOption);
				teamList = JSON.parse(data.teamList);
				for(let i = 0; i < teamList.length; i++) {
					const $teamTag = "<option value = '" + teamList[i].teamCode + "'>" + teamList[i].teamName + "</option>" 
					$("#team").append($teamTag);
				}
			},
			error: function(data) {
				console.log("ajax통신 실패 에러");
			}
		});
	});
	
	$("#team").on("change", function() {
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
	});
	
	$(".readonly").keydown(function(e){
        e.preventDefault();
    });
	
	
	$(document).ready(function(){
        
        // .check 클래스 중 어떤 원소가 체크되었을 때 발생하는 이벤트
    
        $(".checkbok").click(function(){  // 여기서 .click은 체크박스의 체크를 뜻한다.
    
            var str = "";  // 여러개가 눌렸을 때 전부 출력이 될 수 있게 하나의 객체에 담는다.
    
            $(".check").each(function(){  // .each()는 forEach를 뜻한다.
    
                if($(this).is(":checked"))  // ":checked"를 이용하여 체크가 되어있는지 아닌지 확인한다.
    
                    str += $(this).val() + " ";  // 체크된 객체를 str에 저장한다.
                    console.log(this);
            });
    
            $("#multiPrint").text(str);  // #multiPrint에 체크된 원소를 출력한다.
    
        });
    
        
    
        // 전부 체크하는 방법
    
        $("#allCheck").click(function(){
    
            if($(this).is(":checked"))  // 먼저 #allCheck가 선택되었는지 확인한다.
    
                            // 체크가 되어있으면 .check의 모든 원소에 checked="checked"를 추가한다.
    
                $(".checkbok").attr("checked","checked")  
    
            else
    
                $(".checkbok").removeAttr("checked")  // 체크가 안 되어있으면 "checked"를 제거한다.
    
                 // 이 때 체크가 되어있는 원소는 안 바뀐다. 어디까지나 체크가 안 된 것들에 대해서만 효과가 있다.
    
            var str = "";  // 여러개가 눌렸을 때 전부 출력이 될 수 있게 하나의 객체에 담는다.
    
            $(".checkbok").each(function(){  // .each()는 forEach를 뜻한다.
    
                if($(this).is(":checked"))  // ":checked"를 이용하여 체크가 되어있는지 아닌지 확인한다.
    
                    str += $(this).val() + " ";  // 체크된 객체를 str에 저장한다.
    
            });
    
            $("#multiPrint").text(str);  // #multiPrint에 체크된 원소를 출력한다.
    
        });
    
    });
    
	
	
	
	
	
	
	
	
	
		$("#backToList").click( function() {
			console.log("123123");
			$("#title-write").val("");
			$("#my-textarea").val("");
			$("#meeting-fileupload").val("");
		});
	</script>
</body>
</html>