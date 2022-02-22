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
	.regist-project {
	    position: absolute;
	    right: 27px;
	    width: 85px;
	    height: 43px;
	}
</style>
</head>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp" />
	<div class="container-fluid px-4">
		<h1 class="mt-4">
			<i class="fas fa-project-diagram"></i> 프로젝트 관리
		</h1>
		<div class="mb-2">
			<div class="col" style="width: 50%; text-align: left;">
				<button type="button"
					onclick="location.href='${ pageContext.servletContext.contextPath }/project/list'"
					class="btn btn-secondary">프로젝트 관리</button>
					<button class="regist-project btn btn-dark"
						onclick="location.href='${ pageContext.servletContext.contextPath }/project/regist'">생성</button>
			</div>
		</div>
		<div class="card mb-4 mt-3">
			<div class="card-header" style="width: 100%;">
				<div class="row">
					<div class="col"
						style="width: 50%; text-align: left; font-weight: bold; font-size: 1.3em">
						<label>프로젝트 관리 페이지</label>
					</div>
	
				</div>
			</div>
			<div class="card-body">
				<table style="width: 100%; text-align: center;">
					<colgroup>
						<col style="width: 10%" />
						<col style="width: 10%" />
						<col style="width: 10%" />
						<col style="width: 10%" />
						<col style="width: 10%" />
						<col style="width: 10%" />
						<col style="width: 10%" />
						<col style="width: 5%" />
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
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="project" items="${ manageProject }">
							<tr>
								<td><c:out value="${ project.name}" /></td>
								<td><c:out value="${ project.member.memberName}" /></td>
								<td><c:out value="${ project.progression }" /></td>
								<td>대기</td>
								<td>대기</td>
								<td><c:out value="${ project.startDate }" /></td>
								<td><c:out value="${ project.deadLine }" /></td>
								<td><button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#projectRemove">삭제</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script>
			const $tds = document.getElementsByTagName("td");
			for (let i = 0; i < $tds.length; i++) {
	
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
	</script>
</body>
</html>