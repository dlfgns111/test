<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="declaration.*" %>
    <jsp:useBean id="dao" class="declaration.DeclarationDAO" />
    <jsp:useBean id="dto" class="declaration.DeclarationDTO" />
    <% 
    dto = dao.read(3);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>

<style>
.active{
color: red;
}
</style>

</head>
<body>
<div class="wrap">
	<div class="head">
	<b><h1>pinchHitters<h1></h1></b>
	<b><h3>신고게시판<h1></h3></b>
	</div>
	
	<div class="navbar">
		<input type="button" value="공지사항" onclick="location.href='./noticeCreateForm.jsp'">
		<input type="button" value="신고게시판" onclick="location.href='./declarationCreateForm.jsp'">
	</div>
	
	<div class="main" align="center">
		<% if(dto.getU_ID() != null){%>
			<input type="button" value="개인" class="active">
		<%} %>
		<% if(dto.getC_ID() != null){%>
			<input type="button" value="업체" class="active">
		<%} %>
		
		<br><br>
		
		<textarea rows="50" cols="50">
		신고내용을 입력해주세요.
		</textarea>
		
		<br><br>
		<input type="button" value="신고등록" onclick="location.href='./declarationCreateProc.jsp'">
	</div>
</div>
</body>
</html>