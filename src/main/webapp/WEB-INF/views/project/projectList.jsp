<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>
			<div id="layoutSidenav_content">
				<main>
                    <div class="container-fluid px-4">
                        <br>
                        <br>
                        <h1 class="mt-4"><i class="fas fa-project-diagram"></i> 프로젝트</h1>
                        <div class="card mb-4">
                            
                        </div>
                        <div class="card mb-4">
                            <div class="card-header" style="width: 100%;">
                                <div class="row">
                                    <div class="col" style="width: 50%; text-align: left; font-weight: bold; font-size: 1.3em">
                                        <label>관리중인 프로젝트</label>
                                    </div>
                                    <div class="col" style="width: 50%; text-align: right;">
                                        <button type="button" onclick="location.href='메인 프로젝트화면.html'" class="btn btn-dark">프로젝트 관리</button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <table style="width:100%; text-align: center;">
                                    <colgroup>
                                        <col style="width:20%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>

                                    </colgroup>

                                    <thead>
                                        <tr>
                                            <th>프로젝트명</th>
                                            <th>담당자</th>
                                            <th>진행률</th>
                                            <th>산출물</th>
                                            <th>이슈</th>
                                            <th>시작일</th>
                                            <th>마감일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1번프로젝트</td>
                                            <td>홍성원</td>
                                            <td>30</td>
                                            <td>5</td>
                                            <td>100</td>
                                            <td>2021.01.01</td>
                                            <td>2021.03.08</td>
                                        </tr>
                                        
                                        <tr>
                                            <td>2번프로젝트</td>
                                            <td>홍성원</td>
                                            <td>20</td>
                                            <td>5</td>
                                            <td>100</td>
                                            <td>2021.02.01</td>
                                            <td>2021.03.08</td>
                                        </tr>
                                        <tr>
                                            <td>3번프로젝트</td>
                                            <td>홍성원</td>
                                            <td>30</td>
                                            <td>51</td>
                                            <td>120</td>
                                            <td>2021.01.21</td>
                                            <td>2021.03.08</td>
                                        </tr>
                                        <tr>
                                            <td>4번프로젝트</td>
                                            <td>김서영</td>
                                            <td>80</td>
                                            <td>40</td>
                                            <td>10</td>
                                            <td>2021.01.01</td>
                                            <td>2021.03.08</td>
                                        </tr>
                                        <tr>
                                            <td>5번프로젝트</td>
                                            <td>김서영</td>
                                            <td>82</td>
                                            <td>42</td>
                                            <td>11</td>
                                            <td>2021.03.01</td>
                                            <td>2021.03.08</td>
                                        </tr>
                                        <tr>
                                            <td>6번프로젝트</td>
                                            <td>김서영</td>
                                            <td>80</td>
                                            <td>40</td>
                                            <td>10</td>
                                            <td>2021.01.01</td>
                                            <td>2021.03.08</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                  <div class="modal-content">
                                    <div class="modal-header">
                                      <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                      </button>
                                    </div>
                                    <div class="modal-body">
                                      <form>
                                        <div class="form-group">
                                          <label for="recipient-name" class="col-form-label">Recipient:</label>
                                          <input type="text" class="form-control" id="recipient-name">
                                        </div>
                                        <div class="form-group">
                                          <label for="message-text" class="col-form-label">Message:</label>
                                          <textarea class="form-control" id="message-text"></textarea>
                                        </div>
                                      </form>
                                    </div>
                                    <div class="modal-footer">
                                      <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                      <button type="button" class="btn btn-primary">Send message</button>
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </div>
                        <div class="card mb-4">
                            <div class="card-header">
                                <label style="font-size: 1.3em; font-weight: bold">참여중인 프로젝트</label>
                            </div>
                            <div class="card-body">
                                <table style="width:100%;text-align: center;">
                                    <colgroup>
                                        <col style="width:20%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>
                                        <col style="width:10%"/>

                                    </colgroup>

                                    <thead>
                                        <tr>
                                            <th>프로젝트명</th>
                                            <th>담당자</th>
                                            <th>진행률</th>
                                            <th>산출물</th>
                                            <th>이슈</th>
                                            <th>시작일</th>
                                            <th>마감일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>12번프로젝트</td>
                                            <td>홍성원</td>
                                            <td>30</td>
                                            <td>5</td>
                                            <td>100</td>
                                            <td>2021.01.01</td>
                                            <td>2021.03.08</td>
                                        </tr>
                                        
                                        <tr>
                                            <td>11번프로젝트</td>
                                            <td>홍성원</td>
                                            <td>20</td>
                                            <td>5</td>
                                            <td>100</td>
                                            <td>2021.02.01</td>
                                            <td>2021.03.08</td>
                                        </tr>
                                        <tr>
                                            <td>23번프로젝트</td>
                                            <td>홍성원</td>
                                            <td>30</td>
                                            <td>51</td>
                                            <td>120</td>
                                            <td>2021.01.21</td>
                                            <td>2021.03.08</td>
                                        </tr>
                                        <tr>
                                            <td>44번프로젝트</td>
                                            <td>김서영</td>
                                            <td>80</td>
                                            <td>40</td>
                                            <td>10</td>
                                            <td>2021.01.01</td>
                                            <td>2021.03.08</td>
                                        </tr>
                                        <tr>
                                            <td>15번프로젝트</td>
                                            <td>김서영</td>
                                            <td>82</td>
                                            <td>42</td>
                                            <td>11</td>
                                            <td>2021.03.01</td>
                                            <td>2021.03.08</td>
                                        </tr>
                                        <tr>
                                            <td>62번프로젝트</td>
                                            <td>김서영</td>
                                            <td>80</td>
                                            <td>40</td>
                                            <td>10</td>
                                            <td>2021.01.01</td>
                                            <td>2021.03.08</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
	
    $('#datatablesSimple tbody tr').click(function () {  
        // get position of the selected row  
        var position = $('#datatablesSimple').dataTable().fnGetPosition(this)  
        // value of the first column (can be hidden)  
        var id = $('#datatablesSimple').dataTable().fnGetData(position).id
        // redirect
        document.location.href = "프로젝트수정페이지.html";
    })


    
    if(document.getElementsByTagName("td")) {
        
        const $tds = document.getElementsByTagName("td");
        for(let i = 0; i < $tds.length; i++) {
            
            $tds[i].onmouseenter = function() {
                this.parentNode.style.backgroundColor = "rgba(29, 22, 22, 0.106)";
                this.parentNode.style.cursor = "pointer";
            }
            
            $tds[i].onmouseout = function() {
                this.parentNode.style.backgroundColor = "white";
                this.parentNode.style.color = "black"
            }
            
            $tds[i].onclick = function() {
                const no = this.parentNode.children[0].innerText;
                location.href = "${ pageContext.servletContext.contextPath }/board/detail?no=" + no;
            }
            
        }
        
    }
        

	</script>



</body>
</html>