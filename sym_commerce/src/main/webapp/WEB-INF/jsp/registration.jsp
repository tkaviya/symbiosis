<%--
  Created by IntelliJ IDEA.
  User: tsungai1
  Date: 2013/04/03
  Time: 10:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form name="registration">
    <table style="width: 500px;">
        <tr>
            <td>Company Name</td>
            <td><input name="company_name" id="company_name" type="text" width="100px"/></td>
        </tr>
        <tr>
            <td>Agent Name</td>
            <td><input name="agent_name" id="agent_name" type="text" width="100px"/></td>
        </tr>
        <tr>
            <td>Company Description</td>
            <td><textarea rows="4" cols="50" name="company_description" id="company_description"/></td>
        </tr>
        <tr>
            <td>Contact Email</td>
            <td><input name="email" id="email" type="text" width="100px"/></td>
        </tr>
        <tr>
            <td>Contact Phone</td>
            <td><input name="phone" id="phone" type="text" width="100px"/></td>
        </tr>

    </table>
</form>