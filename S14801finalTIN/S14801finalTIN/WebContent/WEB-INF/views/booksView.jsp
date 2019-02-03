<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Books List</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Available Books</h3>
 
    <table border="1" cellpadding="3" cellspacing="1" >
       <tr>
          <th>Title</th>
          <th>Author</th>
          <th>ISBN</th>
       </tr>
       <c:forEach items="${booksList}" var="book" >
          <tr>
             <td>${book.title}</td>
             <td>${book.author}</td>
             <td>${book.ISBN}</td>
          </tr>
       </c:forEach>
    </table>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>