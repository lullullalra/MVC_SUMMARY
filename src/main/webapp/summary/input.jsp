<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INPUT</title>
</head>
<body>
<h1>Input</h1>
<!-- <a href="output.do">결과페이지로</a> -->
<form action="output.do" method="post" enctype="multipart/form-data">
<table>
	<tr><td>도서제목</td><td><input type="text" name="bookTitle"/></td><td></td><td></td></tr>
	<tr><td>저자</td><td><input type="text" name="bookAuthor"/></td><td></td><td></td></tr>
	<tr><td>가격</td><td><input type="text" name="bookPrice"/></td><td></td><td></td></tr>
	<tr><td>이미지</td><td><input type="file" name="bookImage"/></td><td></td><td></td></tr>
	<tr><td>첨부파일</td><td><input type="file" name="bookAttache"/></td><td></td><td></td></tr>
	<tr><td><input type="submit" value="도서 등록"/></td><td></td><td></td><td></td></tr>
</table>
</form>
<br/>
<a href="read.do"><button>리스트 보기</button></a>
</body>
</html>