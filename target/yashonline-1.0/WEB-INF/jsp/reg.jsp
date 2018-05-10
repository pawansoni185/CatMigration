<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
    <html>
     <head>
     
     <style>
    .error
    {
        color: #ff0000;
        font-weight: bold;
    }
    .header{
width:100%;
height:60px;
border:2px solid black;
background-color:#7D1935;
text-align:center;

}
.demo{
background-color:#20415D; 
color:#FFF;
}
    </style>
    <title>Registration Form</title>
    <script type="text/javascript">
    window.onload = function() {

        // If sessionStorage is storing default values (ex. name), exit the function and do not restore data
        if (sessionStorage.getItem('name') == "name") {
            return;
        }
        var email = sessionStorage.getItem('emailId');
        if (email !== null) $('#inputEmail').val(email);
        // If values are not blank, restore them to the fields
        var name = sessionStorage.getItem('name');
        if (name !== null) $('#inputName').val(name);
       
        };
    window.onbeforeunload = function() {
        sessionStorage.setItem("name", $('#inputName').val());
        sessionStorage.setItem("emailId", $('#inputEmail').val());
    }
    </script>
     </head>
     
    <body class="demo">
    <div class="header">
<div style="width:90px;height:65px;float:left;" ><img src="/yashonline/images/Yash.jpeg" style="max-width: 100%;max-height: 100%;"/></div>
<h1 style="color:white" align="center">WELCOME TO YASH ONLINE</h1>
</div>
    <h1 style="color:red" align="center">Employee Registration Form</h1>
    <form:form  action="register" name="registration" modelAttribute="userform" onSubmit="return validateForm()" enctype="utf8" method="post">
    <%-- <form:errors path="*" cssClass="error" />   --%>
    <table  align="center" width=40% width="100%" cellspacing="2" cellpadding="2" border="5">
        <!-- <tr>
            <td colspan="2" align="center"><b>Employee Registration Form.</b></td>
            
        </tr> -->
             <tr>
                <td><spring:message code="lbl.Name" text="Name" /><span style="color:red">*</span></td>
                <td><form:input path="name" /></td>
                <td><form:errors path="Name" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="lbl.userName" text="UserName" /><span style="color:red">*</span></td>
                <td><form:input path="username" /></td>
                <td><form:errors path="username" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="lbl.Password" text="Password" /><span style="color:red">*</span></td>
                <td><form:input path="password" /></td>
                <td><form:errors path="password" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="lbl.confirmPassword" text="ConfirmPassword" /><span style="color:red">*</span></td>
                <td><form:input path="confirmPassword" /></td>
                <td><form:errors path="confirmPassword" cssClass="error" /></td>
            </tr>
         <tr>
                <td><spring:message code="lbl.emailId" text="emailId" /><span style="color:red">*</span></td>
                <td><form:input path="emailId" id="inputEmail" /></td>
                <td><form:errors path="emailId" cssClass="error" /></td>
            </tr>
        
       
        <tr>
            <td colspan="2">
            <p align="center">
            <input type="submit" name ="submit" value="Submit" name="B4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="reset" value="Reset All" name="B5">
            <a href="./regsubmit">back</a><td>
        </tr>
     
    </table>
    </form:form>
    </body>
     
    </html>

  <%-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
 function validateForm() {
	
	 if(document.registration.username.value=="")
	    {
	      alert("User Name should be left blank");
	      document.frm.username.focus();
	      return false;
	    }
	    else if(document.registration.passid.value=="")
	    {
	      alert("Password should be left blank");
	      document.frm.pwd.focus();
	      return false;
	    }
} 
</script>

</head>
<body   bgcolor="pink"  >
 <center>
<table cellpadding=4 cellspacing=2 border=0>
<th bgcolor="#CCCCFF" colspan=2>
<font size=5>USER REGISTRATION</font>
<br>
<font size=1><sup>*</sup> Required Fields</font>
</th>

 <div class="panel panel-default">
       
</h1>
<form:form    method="post"  action="register" name="registration"  modelAttribute="user" enctype="utf8" onSubmit="return validateForm()" >
 <div>
<label for="name"> Name
</label>
<div>
<input type="text" name="name" maxlength="12" placeholder="name"></input>
</div>
</div>
 <br> 
 <div>
<label for="username">User Name</label>
<div>
<input type="text" name="username" maxlength="12" placeholder="username"></input>
</div>
</div>
 <br>
<div>
<label for="passid">Password </label>
<div>
<input type="password" name="passid" maxlength="12" placeholder="password"></input>
</div>
</div>
<br>
 <div>
<label for="ConfirmPassword">Confirm  Password </label>
<div>
<input type="password" name="ConfirmPassword" maxlength="12" placeholder="password"></input>
</div>
</div>
 <div>
<label for="emailId">E-mail</label>
<div>
<input type="text" name="emailId" maxlength="50" placeholder="@yash.com"></input>
</div>
@yash.com is mendatarry.
</div>
<div>
<div>
<input type="reset" value="Reset" />
<input type="submit" name="submit" value="Register"/> 
<a href="./regsubmit">back</a>
</div>
</div>
<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

</form:form>

</body>
</html> --%>