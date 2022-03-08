<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.box{
    
    width: 70%;
    height: 80%;
    border-style: solid;
    border-color: white;
}

#outbox{
    position: absolute; left: 21%; top: 10%;
    border-width: 1px;
}
.search-bar{
    width: 500px;
    
}
.select-bar{
    width: 160px;
}
.idtable {
    width: 42%;    
}
.select-phone{
    width: 500px;
}



</style>
</head>
<body>
	<jsp:include page="../common/inprojectheader.jsp"/>
	<jsp:include page="../common/footer.jsp"/>

	<div class="box" id="outbox">
                <main>
                    <div>
                        <div>
                            <svg xmlns="http://www.w3.org/2000/svg" top="1000" width="60" height="31" fill="currentColor" class="bi bi-person-plus-fill" viewBox="1 -2 16 16">
                            <path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                            <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
                            </svg>
                        </div>
                        <div style="position:absolute; left:50px; top: 10px;">
                            <h6><strong>개인정보관리</strong></h6>
                        </div>
                        <hr align="left" style="border: solid 2px rgb(0, 0, 0); width: 100%;">
                        <div>
                            <form action="" method="get">
                                <div>
                                    <button type="button" class="ui secondary button" style="float: right;" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                        비밀번호 변경
                                    </button>
                                </div>
                               
                                <div class="mx-auto search-bar input-group mt-5">
                                    <input type="text" class="form-control" placeholder="아이디" aria-label="Recipient's username" aria-describedby="basic-addon2" style="margin-top: 50px;left: 40;left: 75px;">
                                </div> 
                                <div class="mx-auto search-bar input-group mt-4">
                                    <input type="text" class="form-control" placeholder="이름 입력" aria-label="Recipient's username" aria-describedby="basic-addon2">
                                </div>

                                <div class="mx-auto select-phone input-group mt-4">
                                    <input type="text" class="form-control" placeholder="휴대전화" aria-label="Recipient's username" aria-describedby="basic-addon2">
                                    <button class="ui secondary button">
                                        핸드폰 인증
                                    </button>                                  
                                </div>  
                                
                                <div class="mx-auto select-phone input-group mt-4">
                                    <input type="text" class="form-control" placeholder="휴대폰 인증번호" aria-label="Recipient's username" aria-describedby="basic-addon2">
                                    <button class="ui secondary button" style="padding-left: 43px;">
                                        인증번호 
                                    </button>                                  
                                </div> 

                                <div class="mx-auto select-phone input-group mt-4">
                                    <input type="text" class="form-control" placeholder="이메일" aria-label="Recipient's username" aria-describedby="basic-addon2">
                                    <button class="ui secondary button">
                                        이메일 인증 
                                    </button>                                  
                                </div>
                                
                                <div class="mx-auto select-phone input-group mt-4">
                                    <input type="text" class="form-control" placeholder="이메일 인증번호" aria-label="Recipient's username" aria-describedby="basic-addon2">
                                    <button class="ui secondary button" style="padding-left: 43px;">
                                        인증코드 
                                    </button>                                  
                                </div> 

                                <div class="mt-5 mx-auto" style="width: 16%;" >
                                    <div style="float: left; width: 50%;">
                                        <button class="ui secondary button">
                                            등록
                                        </button>
                                    </div>    
                                    <div style="float: left; width: 50%;">
                                        <button class="ui secondary button">
                                            취소
                                        </button>                                                                                                                        
                                    </div> 
                                </div>
                            </form>
                        </div>
                    </div>
                </main>
            </div>
	
</body>
</html>