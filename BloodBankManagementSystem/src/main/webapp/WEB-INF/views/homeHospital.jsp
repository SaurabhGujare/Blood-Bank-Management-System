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

<a href="${pageContext.request.contextPath}/login/hospital/sendrequest">Send Blood Request</a><br/>
<a href="${pageContext.request.contextPath}/login/hospital/bloodbanksstock">Blood Availability</a><br/>
<a href="${pageContext.request.contextPath}/home">Logout</a><br/>
</div>

</body>
</html>