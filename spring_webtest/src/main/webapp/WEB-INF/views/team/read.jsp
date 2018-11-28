<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 

div{
	text-align: center;
	margin: 10px 0px;
	padding: 5px;
}
table{
	width:60%;
	margin:auto;
}
table,th,td{
	border:1px solid black;
	border-collapse: collapse;
	padding : 5px;
}
</style>
<script type="text/javascript">
function tlist(){
	var url = "list";
	url = url +"?col=${param.col}";
	url = url +"&word=${param.word}";
	url = url +"&nowPage=${param.nowPage}";

	location.href=url;
	
}
</script>
</head> 
<body> 
<div class="container">
<h2>조회</h2>
<table class="table table-bordered">
	<tr>
		<th>이름</th>
		<td>${dto.name}</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>${dto.gender}</td>
	</tr>
	<tr>
		<th>보유기술</th>
		<td>${dto.skills}</td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td>${dto.phone}</td>
	</tr>
	<tr>
		<th>취미</th>
		<td>${dto.hobby}</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${dto.zipcode}</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>${dto.address}<br>
			${dto.address2}
		</td>
	</tr>
	
</table>

<button type="button" onclick="tlist()">목록</button>
</div>
</body> 
</html> 












