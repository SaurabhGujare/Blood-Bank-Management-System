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
<li>DONOR</li>
<li>BLOOD BANK</li>
<li><a href="${pageContext.request.contextPath}/registerhospital">HOSPITAL</a></li>	
</ul>
<br/>
<p><strong>LOGIN</strong></P>

<form:form action="" method=""> 

	<select>
	<option>Select the role</option>
	<option>DONOR</option>
	<option>BLOOD BANK</option>
	<option>HOSPITAL</option>	
	</select><br/>
	
	<input type="input"/><br/>
	<input type="password"/><br/>
	<input type="submit" value="login" /><br/>
</form:form>
</body>
</html>
