package org.tin.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tin.beans.Book;
import org.tin.beans.UserAccount;
import org.tin.utils.DBUtils;
import org.tin.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/createBook" })
public class CreateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateBookServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createBookView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
   	 
        UserAccount loginedUser = MyUtils.getLoginedUser(session);

        if (loginedUser == null) {
	        System.out.println("No login");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
    	
    	Connection conn = MyUtils.getStoredConnection(request);
 
        String title = (String) request.getParameter("title");
        String author = (String) request.getParameter("author");
        String ISBN = (String) request.getParameter("ISBN");

        Book book = new Book(title, author, ISBN);
 
        String errorString = null;
 
        String regex = "\\d+";
        
        
        if (ISBN == null || !ISBN.matches(regex)) {
            errorString = "ISBN Code invalid!";
        }
 
        if (errorString == null) {
            try {
                DBUtils.insertBook(conn, book);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
 
        request.setAttribute("errorString", errorString);
        request.setAttribute("book", book);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createBookView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/bookList");
        }
    }
 
}