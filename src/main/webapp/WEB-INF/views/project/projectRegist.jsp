<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/inprojectheader.jsp"/>

	<div class="container-fluid px-4" style="margin-left:2%">
		<br>
		<br>
		<h1 class="mt-4" >프로젝트 생성</h1>
		<div class="card mb-4"style="width: 95%;">
		</div>
		<form>
			<div class="card mb-4" style="width: 95%;">
				<div class="card-header col" style="width: 100%">
					<label style="width: 15%;">프로젝트 수정</label>
					<span style="display:inline-block; width: 80%; margin-left: 2em; text-align: right;">
						<button class="btn-dark" style="width: 80px; height: 40px;">취소</button>
						<button class="btn-dark" style="width: 80px; height: 40px;">저장</button>
					</span>
				</div>
				<div class="mt-4">
					<label style="margin-left: 2%; width: 20%">프로젝트명</label>
					<input type="text" name="projectName">
				</div>
				<div class="mt-4">
					<label style="margin-left: 2%; width: 20%">시작일</label>
					<input type="date" name="startDate"><br>
					<label class="mt-3" style="margin-left: 2%; width: 20%">종료일</label>
					<input type="date" name="deadLine">
				</div>
				<div class="mt-4">
					<label style="margin-left: 2%; width: 20%">프로젝트 매니저</label>
					<input type="text" readonly value="PM이름">
				</div>
				<div class="mt-4">
					<span  style="margin-left: 2%">
						<select id="dept" name="dept">
							<option value="" selected disabled>부서 선택</option>
							<c:forEach var="dept" items="${ deptList }">
								<option value="${ dept.no }"><c:out value="${ dept.name }"/></option>
							</c:forEach>
						</select>
					</span>
					<span>
						<select id="team" name="team">
							<option value="" selected disabled>팀 선택</option>
							<c:forEach var="dept" items="${ deptList }">
								<option value="${ dept.no }"><c:out value="${ dept.name }"/></option>
							</c:forEach>
						</select>
					</span>
					<span>
						<select id="dept" name="dept">
							<option value="" selected disabled>사원 선택</option>
							<c:forEach var="dept" items="${ deptList }">
								<option value="${ dept.no }"><c:out value="${ dept.name }"/></option>
							</c:forEach>
						</select>
					</span>
					<span>
					    <input type="button" value="변경">
					</span>
				</div>
				<div class="mt-3">
					<label style="margin-left: 2%; width: 20%">진행상태</label>
					<select style="width: 10%;">
					    <option selected="selected">개발중</option>
					    <option>중단</option>
					    <option>납품완료</option>
					</select>
				</div>
				<div class="col" style="text-align: center; margin-right: 2em; margin-bottom: 2em; margin-top: 4em">
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
</body>
</html>