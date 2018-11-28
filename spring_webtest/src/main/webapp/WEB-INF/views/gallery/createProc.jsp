<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp"%>
<%-- <%
	boolean flag = (Boolean) request.getAttribute("flag");
%>
 --%>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
	function glist() {
		var url = "list";
		location.href = url;
	}
	function gcreate() {
		var url = "create";
		location.href = url;
	}
</script>
</head>
<body>

	<DIV class="title">
		<span class="glyphicon glyphicon-picture"></span>처리결과
	</div>
	<div class="content">
		<c:choose>
			<c:when test="${flag }">사진 등록에 성공하였습니다.</c:when>
			<c:otherwise>사진 등록에 실패하였습니다.</c:otherwise>
		</c:choose>
	</div>

	<DIV class='bottom'>

		<c:choose>
			<c:when test="${flag }">

				<button type="button" class="btn btn-default btn-sm"
					onclick="glist()">
					<span class="glyphicon glyphicon-list"></span> 목록
				</button>
				<button type="button" class="btn btn-default btn-sm"
					onclick="gcreate()">
					<span class="glyphicon glyphicon-pencil"></span> 계속등록
				</button>

			</c:when>
			<c:otherwise>

				<button type="button" class="btn btn-default btn-sm"
					onclick="glist()">
					<span class="glyphicon glyphicon-list"></span> 목록
				</button>
				<button type="button" class="btn btn-default btn-sm"
					onclick="history.back()">
					<span class="glyphicon glyphicon-repeat"></span> 다시시도
				</button>

			</c:otherwise>
		</c:choose>
	</DIV>
	<%-- <DIV class="title">
 <span class="glyphicon glyphicon-picture"></span>처리결과</div>
<div class="content">
<%
	if (flag) out.print("사진 등록에 성공하였습니다.");
	else out.print("사진 등록에 실패하였습니다.");
	%>
</div>  
  <DIV class='bottom'>
  <%if (flag){%>
  <button type="button" class="btn btn-default btn-sm" onclick="glist()"><span class="glyphicon glyphicon-list"></span> 목록</button>
  <button type="button" class="btn btn-default btn-sm" onclick="gcreate()"><span class="glyphicon glyphicon-pencil"></span> 계속등록</button>
  
  <%} else { %>
  
  <button type="button" class="btn btn-default btn-sm" onclick="glist()"><span class="glyphicon glyphicon-list"></span> 목록</button>
  <button type="button" class="btn btn-default btn-sm" onclick="history.back()"><span class="glyphicon glyphicon-repeat"></span> 다시시도</button>
  <%} %>
  </DIV> --%>

</body>
</html>
