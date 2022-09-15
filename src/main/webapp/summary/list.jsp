<%@page import="gntp.lesson.mvc.vo.BookVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
<style>
	table {
		border-collapse: collapse;
		border: 1px solid black;
	}
	
	tr, th, td{
		text-align: center;
	}
</style>
<script>
	let list = []; 
	 
	const name = document.getElementsByName("name");
	for(i=0; i<name.length(); i++){
		if(name[i].isChecked==true){
			list[i] = name[i].value;
		}
	}
</script>
</head>
<body>
<h1>Book List</h1>
<form action="list.do" method="post">

	<table border="1">
	<tr><th>분류번호</th><th>제목</th><th>저자</th><th>가격</th><th>이미지</th><th>첨부파일</th></tr>
<%	ArrayList<BookVO> list = (ArrayList<BookVO>)request.getAttribute("list"); 
	for(BookVO book : list){
%>
		<tr>
			<td><%=book.getBookSeq() %></td>
			<td><%=book.getBookTitle() %></td>
			<td><%=book.getBookAuthor() %></td>
			<td><%=book.getBookPrice() %></td>
			<td><img src="<%="C:\\dev\\eclipse\\gntp\\images\\"+book.getBookImage()%>" alt="" width="50" height="50"></td>
			<td><%=book.getBookAttachment() %></td>
			<td><input type="checkbox" name="name" value=<%=book.getBookSeq() %>></td>
		</tr>
<%} %>		
	</table>
	<input type="submit" value="홈으로">  
</form>
</body>
</html>