<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div>
   <form:form method="POST" action="saveTest" commandName="test">
   <table>
    <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
    	<td><form:select path="block" items="${blocks}" itemValue="id" itemLabel="name" /></td>
    </tr>
    <tr>
        <td><form:label path="description">Description</form:label></td>
        <td><form:input path="description" /></td>
    </tr>
     <tr>
     	<td><form:label path="isAlive"> Active </form:label></td>
     	<td><form:input path="isAlive" /></td>
     </tr>  
      <tr>
     	<td><form:label path="isDeleted"> Deleted </form:label></td>
     	<td><form:input path="isDeleted" /></td>
     </tr>  
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
        
    </tr>
</table> 
</form:form>
</div>