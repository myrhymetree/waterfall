<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
		<div class="sb-nav-link-icon">
			<i class="fas fa-book-open"></i>
		</div>
		<div class="menu-font">산출물</div>
		<div class="sb-sidenav-collapse-arrow">
			<i class="fas fa-angle-down"></i>
		</div>
	</a>
	<div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
		<nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
			<a class="nav-link menu-font2" href="${ pageContext.servletContext.contextPath }/output/admin/list?no=" + "${ projectAutority.projectNo }">
				산출물관리
			</a>
			<div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
				<nav class="sb-sidenav-menu-nested nav">
					<a class="nav-link" href="login.html">직급관리</a> 									
				</nav>
			</div>
		</nav>
	</div>
	<a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages2" aria-expanded="false" aria-controls="collapsePages2">
		<div class="sb-nav-link-icon">
			<i class="fas fa-book-open"></i>
		</div>
		<div class="menu-font">이슈</div>
		<div class="sb-sidenav-collapse-arrow">
			<i class="fas fa-angle-down"></i>
		</div>
	</a>
	<div class="collapse" id="collapsePages2" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
		<nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
			<a class="nav-link collapsed, menu-font2" href="${ pageContext.servletContext.contextPath }/issue/list" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
				이슈관리
			</a>						
			<nav class="sb-sidenav-menu-nested nav">
				<a class="nav-link" href="${ pageContext.servletContext.contextPath }/issue/task">이슈 조회</a> 									
<%-- 				<a class="nav-link" href="${ pageContext.servletContext.contextPath }/issue/list?no=" +  + "${ projectAutority.projectNo }">이슈 조회</a> 									 --%>
			</nav>				
		</nav>
	</div>
	<a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts5" aria-expanded="false" aria-controls="collapseLayouts5">
		<div class="sb-nav-link-icon">
			<i class="fas fa-columns"></i>
		</div>
		<div class="menu-font">히스토리</div>
		<div class="sb-sidenav-collapse-arrow">
			<i class="fas fa-angle-down"></i>
		</div>
	</a>
	<div class="collapse" id="collapseLayouts5" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
		<div class="menu-font2">
			<nav class="sb-sidenav-menu-nested nav">
				<a class="nav-link" href="${ pageContext.servletContext.contextPath }/history/project">프로젝트</a> 
				<a class="nav-link" href="${ pageContext.servletContext.contextPath }/history/task">업무</a> 
				<a class="nav-link" href="${ pageContext.servletContext.contextPath }/history/issue">이슈</a> 
				<a class="nav-link" href="${ pageContext.servletContext.contextPath }/history/output">산출물</a> 
				<a class="nav-link" href="${ pageContext.servletContext.contextPath }/history/member">인원배정</a>
				<a class="nav-link" href="${ pageContext.servletContext.contextPath }/history/board">게시판</a>
			</nav>
		</div>
	</div>
	<a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts6" aria-expanded="false" aria-controls="collapseLayouts6">
		<div class="sb-nav-link-icon">
				<i class="fas fa-columns"></i>
		</div>
		<div class="menu-font">인원관리</div>
		<div class="sb-sidenav-collapse-arrow">
			<i class="fas fa-angle-down"></i>
		</div>
	</a>
	<div class="collapse" id="collapseLayouts6" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
		<div class="menu-font2">
			<nav class="sb-sidenav-menu-nested nav">
				<a class="nav-link" href="${ pageContext.servletContext.contextPath }/manage/member/list">배정조회</a> 
			</nav>
		</div>
	</div>
</body>
</html>