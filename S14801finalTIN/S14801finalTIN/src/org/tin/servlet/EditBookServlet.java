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

import org.tin.beans.Book;
import org.tin.utils.DBUtils;
import org.tin.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/editBook" })
public class EditBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditBookServlet() {
        super();
    }
 
    // Show book edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String ISBN = (String) request.getParameter("ISBN");
 
        Book book = null;
 
        String errorString = null;
 
        try {
            book = DBUtils.findBook(conn, ISBN);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The book does not exist to edit.
        // Redirect to bookList page.
        if (errorString != null && book == null) {
            response.sendRedirect(request.getServletPath() + "/bookList");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("book", book);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editBookView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the book information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String title = (String) request.getParameter("title");
        String author = (String) request.getParameter("author");
        String ISBN = (String) request.getParameter("ISBN");

        Book book = new Book(title,author,ISBN);
 
        String errorString = null;
 
        try {
            DBUtils.updateBook(conn, book);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("book", book);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editBookView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the book listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/bookList");
        }
    }
 
}