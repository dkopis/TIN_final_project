<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Edit Book</title>
   </head>
   <body>
 
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Edit Book</h3>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty book}">
         <form method="POST" action="${pageContext.request.contextPath}/editBook">
            <input type="hidden" name="ISBN" value="${book.ISBN}" />
            <table border="0">
               <tr>
                  <td>ISBN</td>
                  <td style="color:red;">${book.ISBN}</td>
               </tr>
               <tr>
                  <td>Author</td>
                  <td style="color:black;">${book.author}</td>
               </tr>
               <tr>
                  <td>Title</td>
                  <td><input type="text" name="title" value="${book.title}" /></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/bookList">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
 
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body>
</html>