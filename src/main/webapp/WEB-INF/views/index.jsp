<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
            <!-- m=마진 p=패딩 가운데 정렬 배경색은 검은색 하니까 글자색 흰색 됨 헤 -->
                <h1>메인 페이지</h1>
            </header>
            
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <ul class="navbar-nav bg-light">
                    <li class="nav-item">
                        <c:url value="/post/list" var="postListPage"></c:url>
                        <a class="nav-link" href="${postListPage }">포스트 목록</a>
                    </li>
                </ul>
            </nav>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        </div>
	</body>
</html>