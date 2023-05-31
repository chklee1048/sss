<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
            <h1>포스트 리스트 페이지</h1>
        </header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <ul class="navbar-nav bg-light">
                <li class="nav-item">
                    <c:url value="/" var="mainPage"></c:url>
                    <a class="nav-link"  href="${mainPage }">메인 페이지</a>
                </li>
                <li>
                    <c:url value="/post/create" var="postCreatePage"></c:url>
                    <a class="nav-link"  href="${postCreatePage }">새 포스트 작성</a>
                </li>
            </ul>
        </nav>
		<main class="my-2">
    <!-- 웹 브라우저 커졌다 작아졌다 할 대 유동적. 반응형 웹 대응 -->
    
            <div class="card">
                <table class="card-body table table-success table-striped table-hover">
                    <thead class="table-dark ">
                        <tr>
                            <th class="text-center">번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성시간</th>
                        </tr>
                    </thead>
                        <tbody>
                            <c:forEach items="${ posts }" var="post" >
                                <tr>
                                    <td class="text-center">${ post.id}</td>
                                    <td class="table-danger">
                                        <c:url value="/post/detail" var="postDetailPage">
                                            <c:param name="id" value="${post.id }" />
                                        </c:url>
                                        <a href="${postDetailPage }" >${ post.title}</a> 
                                        <span class="text-danger">[${post.rcnt}]</span>
                                        
                                    </td>
                                    <td>${post.author}</td>
                                    <td>
                                        <fmt:formatDate value="${ post.created_time }"
                                            pattern="yyyy-MM-dd HH:mm" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                </table>
                </div>
            
            <c:url value="/post/search" var="searchPage">
                
            </c:url>
            <form action="${searchPage }">
                <select name="category">
                    <option value="t">제목으로 찾기</option>
                    <option value="c">내용으로 찾기</option>
                    <option value="tc">제목+내용으로 찾기</option>
                    <option value="a">작성자로 찾기</option>
                </select>
                    <input type="text" name="keyword" placeholder="검색어를 입력 하세요." required="required" autofocus="autofocus" />
                    <input type="submit" value="검색" />
            </form>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" 
        crossorigin="anonymous"></script>
        </div>
	</body>
</html>