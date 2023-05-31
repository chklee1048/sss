<%@page import="jakarta.servlet.jsp.tagext.Tag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <h1>포스트 상세 보기</h1>
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
                </ul>
            </nav>
            
            <main class="my-2">
                <section class="card">
                    <form action="" method="post">
                        <div class="card-body">
                            <div class="my-2">
                                <!-- id가 title인 것과 친구 레이블 누르면 번쩍 -->
                                <label class="form-label" for="id" >번호</label>
                                <input type="text" class="form-control" id="id" name="id"  value="${post.id }" readonly="readonly"/>
                            </div>
                            <div class="my-2">
                                <label class="form-label" for="title">제목</label>
                                <input type="text" class="form-control" id="id" name="id" value="${post.title }" readonly/>
                            </div>
                            <div class="my-2">
                                <label class="form-label" for="content">내용</label>
                                <textarea class="form-control" id="content" name="content" readonly >${post.content }</textarea>
                            </div>
                            <div class="my-2">
                                <label class="form-label" for="author">작성자 아이디</label>
                                <input class="form-control" type="text" id="author" name="author"  value="${post.author }" readonly/>
                            </div>
                            <div class="my-2">
                                <label class="form-label" for="createdTime">작성 시간</label>
                                <fmt:formatDate value="${ post.createdTime }" pattern="yyyy-MM-dd HH:mm" var="created"/>
                                <input class="form-control" type="text" id="createdTime" name="createdTime"  value="${created }" readonly/>
                            </div>
                            <div class="my-2">
                                <label class="form-label" for="modifiedTime">수정 시간</label>
                                <fmt:formatDate value="${ post.modifiedTime }" pattern="yyyy-MM-dd HH:mm" var="modified"/>
                                <input class="form-control" type="text" id="modifiedTime" name="modifiedTime"  value="${modified }" readonly/>
                            </div>
                            
                        </div>
                    </form>
                    <div class="card-footer my-2">
                        <c:url value="/post/modify" var="postModifyPage">
                            <c:param name="id" value="${post.id }"></c:param>
                        </c:url>
                        <a class=" btn form-control btn-outline-primary" href="${postModifyPage }">수정하기</a>
                    </div>
                </section>
                
                
                <!-- 댓글 수정 모달 -->
                <div id="replyUpdateModal" class="modal" tabindex="-1">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title">댓글 수정 창</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <div class="modal-body">
                        <!-- 수정할 댓글 아이디 -화면에 보이지 않도록 -->
                        <input id="modalReplyId" class="d-none" />
                        
                        <!-- 수정할 댓글 내용 -->
                        <textarea id="modalReplyText" class="form-control"></textarea>
                        
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                        <button type="button" class="btn btn-primary" id="modalBtnUpdate">저장</button>
                      </div>
                    </div>
                  </div>
                </div>
                
                
            </main> <!-- 포스트 상세보기 끗 -->
            <section class=" my-2 card ">
                    <div class="card-header">
                        <span>댓글</span>
                        <span id="replyCount">${post.replyCount }</span>개 <!-- TODO 실제 댓글 개수 -->
                        <button id="btnToggleReply" class="btn" data-toggle="toggle-off">
                            <img id="toggleBtnIcon" src="/spring2/static/assets/icons/toggle2-off.svg" alt="toggle-off" width="32">
                        </button>
                    </div>
                    <div class="card-body collapse"  id="replyToggleDiv">
                        <!-- 나의 댓글 등록 -->
                        <div class="my-2 row">
                            <label class="form-label" for="replyText">나의 댓글</label>
                            <!-- 부트스트램은 화면 끝까지 가로를 몇개로 나눠서 12개로 나눠서 생각함
                            그래서 컬럼이 12개 있다. 그래서 컬럼 10이 10개 영역을 차지 하겠다. -->
                            <div class="col-10">
                                <textarea class="form-control" id="replyText"></textarea>
                                <input class="d-none" id="writer"  value="admin"/> <!-- TODO 로그인 사용자 넣기 -->
                            </div>
                            <div class="col-2">
                                <button class="form-control btn btn-outline-success" id="btnAddReply">등록</button>
                            </div>
                        </div>
                        <!-- 댓글 목록 보여줄 영역 -->
                        <div class="my-2 row" id="replies"></div>
                    </div>
            </section><!-- 댓글 등록, 댓글 리스트 끗 -->
			
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" 
		        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" 
        		crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
            <script src="../static/js/reply.js"></script>
		</div>
	</body>
</html>