<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${ pageContext.servletContext.contextPath }/resources/css/login.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body class="bg-primary">

<!-- 로그인 머리 부분 시작 -->
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.html">Project Waterfall Service</a>
  
        </nav>
        <!-- 로그인 머리 부분 끝  -->

        <!-- 바디 영역 부분시작 -->
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <form>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5" style="margin-top: 12%">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header">
                                        <h3 class="text-center my-4" style="color: white;">Login</h3>
                                    </div>
                                    <div class="card-body">
                                        <form id="loginForm" action="${ pageContext.servletContext.contextPath }/member/login" method="post">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" type="text" name="id" placeholder="id"/>
                                                <label for="inputEmail">ID</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control"type="password" name="pwd" placeholder="Password" />
                                                <label for="inputPassword">Password</label>
                                            </div>
                                            <div class="form-check mb-3">
                                                <input class="form-check-input" id="inputRememberPassword" type="checkbox" value="" />
                                                <label class="form-check-label" for="inputRememberPassword">Remember ID</label>
                                            </div>
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <p class="small">비밀번호 관련 문의 : 010-xxxx-xxxx</p>
                                                <input class="btn btn-primary" type="submit" value="login" id="login"/>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small">
                                            <strong style="color: white;"> Wellcome To PMS !!</strong>
                                        </div>     
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <!-- 바디 영역 부분 끝 -->


            <!-- 여기서부터 하단 FOOTER 시작 -->
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; 민트초코 4ever 2022</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>

        <!-- 하단 끝 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${ pageContext.servletContext.contextPath }resource/js/scripts.js"></script>
</body>
</html>