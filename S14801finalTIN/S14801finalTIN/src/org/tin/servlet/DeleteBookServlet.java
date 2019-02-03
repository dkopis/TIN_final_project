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

import org.tin.beans.UserAccount;
import org.tin.utils.DBUtils;
import org.tin.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/deleteBook" })
public class DeleteBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
public DeleteBookServlet() {
    super();
}

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	
	HttpSession session = request.getSession();
	 
    UserAccount loginedUser = MyUtils.getLoginedUser(session);

    if (loginedUser == null) {
        System.out.println("No login");
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
	
    Connection conn = MyUtils.getStoredConnection(request);

    String ISBN = (String) request.getParameter("ISBN");

    String errorString = null;

    try {
        DBUtils.deleteBook(conn, ISBN);
    } catch (SQLException e) {
        e.printStackTrace();
        errorString = e.getMessage();
    } 
     
    // If error, redirect to the error page.
    if (errorString != null) {
        request.setAttribute("errorString", errorString);
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/deleteBookErrorView.jsp");
        dispatcher.forward(request, response);
    }   
    else {
        response.sendRedirect(request.getContextPath() + "/bookList");
    }

}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    doGet(request, response);
}

}