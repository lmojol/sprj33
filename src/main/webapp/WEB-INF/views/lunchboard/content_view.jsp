<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./../resources/css/nstyle.css" />
</head>
<body>
<h2>lunch content_view.jsp</h2>
<table>
	<form action="content_update" method="post">
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
			<td>${content_view.lmenu }</td>
		</tr>
		<tr>
			<td class="left">lcontent</td>
			<td>${content_view.lcontent }</td>
		</tr>
		
		<tr>
			<td class="left">lprice</td>
			<td>${content_view.lprice }</td>
		</tr>	
		<tr>
			<td class="left">lfileimg</td>
			<td>
				${content_view.lfileimg }	
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정폼" /> &nbsp;&nbsp;
				<a href="list">목록보기</a> &nbsp;&nbsp;
			</td>
		</tr>
	</form>

</table>
<hr />
<%-- <c:set value="${content_view.filesrc }" var="filename" />
<c:set var="fileNm" value="${fn:toLowerCase(filename)}" />
<c:forTokens var="token" items="${fileNm}" delims="." varStatus="status">
 <c:if test="${status.last}">
   <c:choose>
    <c:when test="${token eq 'jpg'}">
     ${filename}::::그림파일 jpg <br />
     ${status.last}
     <div>
		<img border="1" width="100" height="100" 
		src="resources/upload/${content_view.filesrc }" alt="" />
	</div>
    </c:when>
    <c:when test="${token eq 'xsl' || token eq 'xlsx'}">
     ${filename}::::엑셀파일
    </c:when>
    <c:when test="${token eq 'jpg' || token eq 'png' || token eq 'bmp' }">
     ${filename}::::이미지파일
    </c:when>
   </c:choose>
  </c:if>
	
</c:forTokens>


<c:if test="${content_view.filesrc ne null}">
	<div>
		<img border="1" width="100" height="100" 
		src="resources/upload/${content_view.filesrc }" alt="" />
	</div>
</c:if>
 --%>

</body>
</html>