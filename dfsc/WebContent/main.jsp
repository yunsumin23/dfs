<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
<link rel="stylesheet" href="css/main.css">
<script src="js/main.js"></script>
</head>
<body>
	<section>
		<form id="searchForm" action="main.Controller?url=CSearch"
			method="POST">
			<select name="server">
				<option value="all">전체</option>
				<option value="adven">모험단</option>
				<option value="anton">안톤</option>
				<option value="bakal">바칼</option>
				<option value="cain">카인</option>
				<option value="casillas">카시야스</option>
				<option value="diregie">디레지에</option>
				<option value="hilder">힐더</option>
				<option value="prey">프레이</option>
				<option value="siroco">시로코</option>
			</select> <input type="text" id="text" name="name" placeholder="캐릭터명을 입력해주세요.">
			<input type="submit" value="검색">
		</form>
	</section>

	<footer>
		<a href="http://developers.neople.co.kr" target="_blank">
		<img src="img/neople.png" alt="Neople 오픈 API"/ ></a>
	</footer>
</body>
</html>