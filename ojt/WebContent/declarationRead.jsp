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
<div>

<% if(dto.getA_ID() != null){%>
	<input type="button" value="관리자답변완료" class="active">
<%} %>
<% if(dto.getU_ID() != null){%>
	<input type="button" value="개인" class="active">
<%} %>
<% if(dto.getC_ID() != null){%>
	<input type="button" value="업체" class="active">
<%} %>

</div>
</body>
</html>