<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.header{
width:100%;
height:60px;
border:2px solid black;
background-color:#7D1935;
text-align:center;

}

</style>
</head>
<body bgcolor="gray">
<div class="header">
<div style="width:90px;height:65px;float:left;" ><img src="/yashonline/images/Yash.jpeg" style="max-width: 100%;max-height: 100%;"/></div>
<h1 style="color:white" align="center">WELCOME TO YASH ONLINE</h1>
</div>
<p><center><h3><font color="white"><b> YASH TECHNOLOGIES PVT. LTD.  </h3></center>  </b></font> </p></div></div>
<div>
 
	<div style="width:90px;height:55px;float:center;" ><img src="/yashonline/images/yash1.png"/></div>
	<form name='loginForm'
		action="<c:url value='/j_spring_security_check' />" method='POST'>
		 <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                   
                                    <label style="color:red">Invalid username or password!!</label>
                                </div></c:if>
                 <%--    <a href="<c:url value='/list' />">list</a>           --%>   

		<table align="right" width=40% width="100%" cellspacing="2" cellpadding="2" border="5">
		<tr>
            <td colspan="2" align="center" style="color:red"><b>LOGIN FORM</b></td>
            
        </tr>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> </td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" /></td>
				<td colspan='2'><input name="reset" type="reset" /></td>
			</tr>
<tr>
 <td>New User&emsp;&emsp;&emsp;<a href="<c:url value='/reg' />">SignUp</a></td>
 <td><a href="<c:url value='/forgot' />">Forgot password</a></td>
</tr>
		</table><br>
		
	<!-- <div style="width:90px;height:55px;float:center;" ><img src="/yashonline/images/Web-Forum-2.jpg"/></div>	 -->
		
	</form>
</body>
</html>