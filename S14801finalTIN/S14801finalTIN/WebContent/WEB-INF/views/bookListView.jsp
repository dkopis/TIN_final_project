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
 
    <h3>Books List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Title</th>
          <th>Author</th>
          <th>ISBN</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${bookList}" var="book" >
          <tr>
             <td>${book.title}</td>
             <td>${book.author}</td>
             <td>${book.ISBN}</td>
             <td>
                <a href="editBook?ISBN=${book.ISBN}">Edit</a>
             </td>
             <td>
                <a href="deleteBook?ISBN=${book.ISBN}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>
 
    <a href="createBook" >Create Book</a>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>