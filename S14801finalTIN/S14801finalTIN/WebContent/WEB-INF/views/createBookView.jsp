<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Create Book & connection between authors</title>
   </head>
   <body>
    
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
       
      <h3>Create Product</h3>
       
      <p style="color: red;">${errorString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/createBook">
         <table border="0">
            <tr>
               <td>Title</td>
               <td><input type="text" name="title" value="${book.title}" /></td>
            </tr>
            <tr>
               <td>Author</td>
               <td><input type="text" name="author" value="${book.author}" /></td>
            </tr>
            <tr>
               <td>ISBN</td>
               <td><input type="text" name="ISBN" id="numberField" value="${book.ISBN}" /></td>
            </tr>
            <tr>
            <script language="javascript">
	            function validate(numberField){
	            	var valid=true;
	            	
	            		console.log("TEST");
	            		var numberformat = /^[0-9]+$/;
	            		if(!numberField.value.match(numberformat))
	            		{
	            			console.log(numberField.value.match(numberformat));
	            			valid=false;
	            			alert("Wrong ISBN number! Remember - only numbers");
	            		}
	            		
	            	return valid;
	            }
			</script>
               <td colspan="2">                   
                   <button type="submit" value="Submit"  onclick="validate(numberField)"/>Submit!</button>
                   <a href="bookList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
       
      <jsp:include page="_footer.jsp"></jsp:include>
       
   </body>
</html>