<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Welcome to Blood Bank Management System  
</h1><br/><br/>

<p><strong>Register</strong></P>
<ul>
<li><a href="${pageContext.request.contextPath}/registerdonor">DONOR</a></li>
<li><a href="${pageContext.request.contextPath}/registerbloodbank">BLOOD BANK</a></li>
<li><a href="${pageContext.request.contextPath}/registerhospital">HOSPITAL</a></li>	
</ul>
<br/>
<p><strong>LOGIN</strong></P>

<form:form action="${pageContext.request.contextPath}/login" method="post"> 

	<select name="role">
	<option>Select the role</option>
	<option value="Donor">DONOR</option>
	<option value="BloodBank">BLOOD BANK</option>
	<option value="Hospital">HOSPITAL</option>	
	</select><br/>
	
	<input type="text" name="loginUserName"/><br/>
	<input type="password" name="loginPassword"/><br/>
	<input type="submit" value="login" /><br/>
</form:form>
</body>
</html>
