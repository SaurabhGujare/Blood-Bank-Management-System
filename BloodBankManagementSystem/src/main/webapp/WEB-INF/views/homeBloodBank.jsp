<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% System.out.println("on page Blood Bank Dashboard");  %>
<h1>Blood Bank Login Home Page</h1><br/>
<div>
<a href="${pageContext.request.contextPath}/login/bloodbank/donateform">DONATE</a><br/>
<a href="${pageContext.request.contextPath}/login/bloodbank/searchdonor">SEARCH DONOR</a></br>
<a href="${pageContext.request.contextPath}/login/bloodbank/stocks">BLOOD BANK STOCK</a></br>
<a href="">BLOOD REQUESTS</a></br>
<a href="${pageContext.request.contextPath}/home">Logout</a>

</div>


</body>
</html>