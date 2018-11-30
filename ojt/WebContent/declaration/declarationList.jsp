<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrap">
	<div class="top">
		<b><h1>pinchHitters<h1></h1></b>
		<b><h3>신고게시판(리스트)<h1></h3></b>
	</div>
	
	<div class="navbar">
		<input type="button" value="공지사항" onclick="location.href='../notice/noticeCreateForm.jsp'">
		<input type="button" value="신고게시판" onclick="location.href='./declarationCreateForm.jsp'">
	</div>
	
	<div class="main" align="center">
	리스트
	<br><br>
	<input type="button" value="신고등록" onclick="location.href='./declarationCreateForm.jsp'">
	</div>
	
</div>
</body>
</html>