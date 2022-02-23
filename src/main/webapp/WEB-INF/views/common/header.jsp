<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="description" content="" />
<meta name="author" content="" />
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<link href="css/button.css" rel="stylesheet" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<link href="${ pageContext.servletContext.contextPath }/resources/css/styles.css" rel="stylesheet" />
<script src="${ pageContext.servletContext.contextPath }/resources/js/script.js"></script>
</head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.html">Project Waterfall Service</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
               
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">                       
                        <li><a class="dropdown-item" href="#!">계정정보 관리</a></li>
                        <li><a class="dropdown-item" href="#!">프로필 관리</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="${ pageContext.servletContext.contextPath }/member/logout">로그아웃</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            
                            <div class="sb-sidenav-menu-heading"></div>
                           
                           
                           <c:if test="${ !empty sessionScope.loginMember and sessionScope.loginMember.role eq 1 }">
                           
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="menu-font">관리자메뉴</div>
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
		                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
		                                <div class="menu-font2">
		                                     <nav class="sb-sidenav-menu-nested nav">
		                                        <a class="nav-link" href="layout-static.html">계정관리</a>
		                                        <a class="nav-link" href="layout-sidenav-light.html">직급관리</a>
		                                        <a class="nav-link" href="layout-sidenav-light.html">부서관리</a>
		                                        <a class="nav-link" href="layout-sidenav-light.html">산출물</a>
		                                        <a class="nav-link" href="layout-sidenav-light.html">이슈</a>
		                                    </nav>
		                                </div>
		                            </div>
                           </c:if>
		                            
		                            
		                            
		                            
		                            
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <!-- <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div> -->
                                <div class="menu-font">프로젝트</div>
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                	<div class="menu-font2">
                                	<a href=" ${ pageContext.servletContext.contextPath }/project/list"> 프로젝트</a>
<%--                                 		<c:forEach var="project" items="${ requestScope.projectList }"> --%>
<!--                                     			<a class="nav-link collapsed" z data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth"> -->
<%--                                       		  <c:out value="${ project.name }" /> --%>
<!--                                         			<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div> -->
<%--                                      	  	  <input type="hidden" value="${ project.no }"/> --%>
<!--                                     			</a> -->
<%--                                    		 </c:forEach> --%>
                                	</div>                               
                                </nav>
                            </div>                          
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                         Administrator
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
    </body>
</html>