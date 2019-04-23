<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% System.out.println("on page HomeHospital");  %>
<h1>Hospital's Login Home Page</h1><br/></br>

<div>

<a href="${pageContext.request.contextPath}/hospital/sendrequest">Send Blood Request</a>
<a href="${pageContext.request.contextPath}/hospital/bloodbanksstock">Blood Availability</a>
<a href="${pageContext.request.contextPath}/home">Logout</a>
</div>

</body>
</html>