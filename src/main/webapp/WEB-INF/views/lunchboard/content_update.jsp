<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./../resources/css/nstyle.css" />
</head>
<body>
<h2>content_update.jsp</h2>
<table>
	<form action="modify" method="post">
	<input type="hidden" name="lno" value="${content_view.lno }" />
		<tr>
			<td class="left">lno</td>
			<td>${content_view.lno }</td>
		</tr>
		<tr>
			<td class="left">lhit</td>
			<td>${content_view.lhit }</td>
		</tr>
		<tr>
			<td class="left">lmenu</td>
			<td>
				<input type="text" name="lmenu" value="${content_view.lmenu }" />
			</td>
		</tr>
		<tr>
			<td class="left"> lcontent </td>
			<td> <textarea name="lcontent"  rows="10">${content_view.lcontent }</textarea> </td>
		</tr>
		<tr>
			<td class="left">lprice</td>
			<td>
				<input type="text" name="lprice" value="${content_view.lprice }" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정하기" /> &nbsp;&nbsp;
				<a href="list">목록보기</a> &nbsp;&nbsp;
			</td>
		</tr>
	</form>

</table>

</body>
</html>