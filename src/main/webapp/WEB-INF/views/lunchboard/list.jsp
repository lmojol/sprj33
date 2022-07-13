<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./../resources/css/nstyle.css" />
</head>
<body>
<h3>lunchlist.jsp</h3>
페이징과 댓글은 과감히 주석처리한 상태!!!
<hr />
반가반가 ${mnn }님!
<hr />
<c:if test="${mid eq null }">
	<a href="../login/loginform">login</a>
</c:if>
 | 
 <c:if test="${mid ne null }">
 	<a href="../login/logout">logout</a>
 |
 </c:if>
<a href="../join/joinform">join</a> <br />
<hr />

<table>
	<tr>
		<th>lno</th>
		<th>lmenu</th>
		<th>lfileimg</th>
		<th>modify</th>
		<th>del</th>
	</tr>
	
	<c:forEach items="${list }" var="dto">
		<tr>
			<td>${dto.lno }</td>
			<td>
			<!-- 	들여쓰기가 되도록 -->
			<c:set value="${dto.lindent }" var="endindent" />
			<c:forEach begin="1" end="${dto.lindent }" var="cnt">
				&nbsp;
				<c:if test="${cnt eq  endindent}">
				<img src="resources/img/reply.gif" alt="" />
				[re]
				</c:if>	
			</c:forEach>
				<a href="content_view?bid=${dto.lno }">${dto.lmenu }</a> 
			</td>
			<td>
				<img border="1" width="100" height="100" 
					src="./../resources/upload/${dto.lfileimg }" alt="" />
			
			</td>
			<td>
				<a href="content_view?lno=${dto.lno }">mod</a>
			</td>
			<td>
				<a href="delete?lno=${dto.lno }">del</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="5">
		 <c:if test="${mid ne null }">
			<a href="write_view">글작성</a> 
		 </c:if>
		</td>
	</tr>

</table>
TotCnt : ${totRowCnt }
<br />
<%-- <form id="form1" name="form1" action="list" method="post">
	<c:if test="${searchVO.totPage>1 }">
		<c:if test="${searchVO.page>1 }">
			<a href="list?page=1">[처음]</a>
			<a href="list?page=${searchVO.page-1 }">[이전]</a>
		</c:if>
		<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
			<c:choose>
				<c:when test="${i eq searchVO.page }">
					<span style="color:red; font-weight: bold;">${i } &nbsp;</span> 
				</c:when>
				<c:otherwise>
					<a href="list?page=${i }" style="text-decoration: none">${i } </a>&nbsp;
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${searchVO.totPage>searchVO.page }">
			<a href="list?page=${searchVO.page+1 }">[다음]</a>
			<a href="list?page=${searchVO.totPage }">[마지막]</a>
		</c:if>
	</c:if>
	<div>
		<c:choose>
			<c:when test="${btitle }">
				<input type="checkbox" name="searchType" value="btitle" checked />
			</c:when>
			<c:otherwise>
				<input type="checkbox" name="searchType" value="btitle" />
			</c:otherwise>
		</c:choose>
		<label>제목</label>
		<c:choose>
			<c:when test="${bcontent }">
				<input type="checkbox" name="searchType" value="bcontent"  checked />
			</c:when>
			<c:otherwise>
				<input type="checkbox" name="searchType" value="bcontent"  />
			</c:otherwise>
		</c:choose>		
		<label>내용</label>
		<input type="text" name="sk" style="width: 150px;" maxlength="50" value="" />
		<input type="submit" name="btn_search" value="검색" />
	</div>
</form> --%>
</body>
</html>