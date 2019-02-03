<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
     <meta charset="UTF-8">
     <title>Home Page</title>
  </head>
  <body>
 
     <jsp:include page="_header.jsp"></jsp:include>
     <jsp:include page="_menu.jsp"></jsp:include>
    
      <h3>Home Page</h3>
      
      This is demo Simple web application using jsp,servlet &amp; Jdbc. <br><br>
      <b>It includes the following functions:</b>
      <ul>
         <li>Login</li>
         <li>Registration&storing hashed passwords</li>
         <li>Page "Books List" is availible for all users, but "Administrator: Book List" is availible only for admin</li>
         <li>Storing user information in cookies</li>
         <li>Book List</li>
         <li>Create Book</li>
         <li>Edit Book</li>
         <li>Delete Book</li>
      </ul>
 
     <jsp:include page="_footer.jsp"></jsp:include>
 
  </body>
</html>