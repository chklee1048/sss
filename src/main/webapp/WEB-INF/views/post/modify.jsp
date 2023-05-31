<%@page import="jakarta.servlet.jsp.tagext.Tag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Spring2</title>
        <link 
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" 
            crossorigin="anonymous">
	</head>
	
	<body>
		<div class="container-fluid">
			<header class="my-2 p-5 text-center text-bg-dark">
                <h1>포스트 수정하기</h1>
            </header>
            
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <ul class="navbar-nav bg-light">
                    <c:url value="/" var="mainPage" />
                    <li class="nav-item">
                        <a class="nav-link" href="${mainPage }">메인 페이지</a>
                    </li>
                
                    <c:url value="/post/list" var="postListPage"></c:url>
                    <li class="nav-item">
                        <a class="nav-link" href="${postListPage }">포스트 목록</a>
                    </li>
                    
                    <c:url value="/post/detail" var="postDetailPage">
                        <c:param name="id" value="${post.id }"></c:param>
                    </c:url>
                    <li class="nav-item">
                        <a class="nav-link" href="${postDetailPage }">포스트 상세보기</a>
                    </li>
                </ul>
            </nav>
            
            <main class="my-2">
                <div class="card">
                    <form id="postModifyForm" method="post">
                        <div class="card-body">
                            <div class="my-2">
                                <!-- id가 title인 것과 친구 레이블 누르면 번쩍 -->
                                <label class="form-label" for="id" >번호</label>
                                <input type="text" class="form-control" id="id" name="id"  value="${post.id }" readonly="readonly"/>
                            </div>
                            <div class="my-2">
                                <label class="form-label" for="title">제목</label>
                                <input type="text" class="form-control" id="title" name="title" value="${post.title }" autofocus="autofocus" required="required"/>
                            </div>
                            <div class="my-2">
                                <label class="form-label" for="content">내용</label>
                                <textarea class="form-control" id="content" name="content" required="required">${post.content }</textarea>
                            </div>
                            <div class="my-2">
                                <label class="form-label" for="author">작성자 아이디</label>
                                <input class="form-control" type="text" id="author" value="${post.author }" readonly/>
                            </div>
                            
                        </div>
                    </form>
                    <!-- 버튼이 만약 form 안에 있다면 e.preventDefault(); 를 써서 폼 버튼 능력 없애라 -->
                    <div class="card-footer ">
                        <div class="d-flex justify-content-center">
                            <button class="mx-2 btn btn-outline-success" id="btnUpdate">업데이트</button>
                            <button class="mx-2 btn btn-outline-danger" id="btnDelete">삭제</button>
                        </div>
                    </div>                    
                </div>
            </main>
			
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" 
		        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" 
        		crossorigin="anonymous"></script>
		</div>
        <!-- <script src="../static/js/post-modify.js"></script> -->
        <!-- 상대경로로 찾을 때에는 WEB-INF는 없는 폴더 취급을 하기 때문에 바로 webapp으로 올라가는 것이다. -->
        <script src="../static/js/post-modify.js"></script>
	</body>
</html>