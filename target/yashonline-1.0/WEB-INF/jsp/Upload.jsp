<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

    </head>
<body>
  <form action="threadAttachment/doUpload" modelAttribute="ThreadAttachment" enctype="multipart/form-data" method="POST">
            <table border="0">
                <tr>
                    <td>Pick file #1:</td>
                    <td><input type="file" name="fileUpload" size="50" /></td>
                </tr>
                              <tr>
                    <td colspan="2" align="center"><input type="submit"  name="doUpload" value="Upload"   /></td>
                </tr>

            </table>
        </form>
</body>
</html>