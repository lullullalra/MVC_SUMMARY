<%@page import="java.net.URLEncoder"%>
<%@page import="gntp.lesson.mvc.vo.BookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OUTPUT</title>
</head>
<style>
	tr, td {
		border : 1px black solid;
	}
	
	table {
		border-collapse : collapse;
	}
</style>
<body>
<form action="read.do" method="post">
<h1>Output</h1>
<%BookVO book = (BookVO) request.getAttribute("book"); %>
<table>
	<tr><td rowspan="3"><img src="<%="C:/dev/eclipse/gntp/images/" +book.getBookImage()%>" alt="" width="300" height="400"/></td><td>제목: <%=book.getBookTitle() %></td></tr>
	<tr><td>저자: <%=book.getBookAuthor() %></td></tr>
	<tr><td>가격: <%=book.getBookPrice() %></td></tr>
	<tr><td>다운로드</td>
		<td>
		<%
			if(book.getBookAttachment()!=null){
		%>
			<a href="download.do?fileName=<%=URLEncoder.encode(book.getBookAttachment(), "UTF-8") %>">
				<%=book.getBookAttachment() %>
			</a>
		<%
			}
		%>
		</td>
	</tr>
	<tr><td colspan="2"><button>확인</button></td></tr>
</table>
</form>
</body>
</html>