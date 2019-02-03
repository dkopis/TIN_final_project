package org.tin.servlet;

	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.SQLException;
	import java.util.List;
	 
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
	 
	@WebServlet(urlPatterns = { "/booksList" })
	public class BooksServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	 
	    public BooksServlet() {
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
	 
	        String errorString = null;
	        List<Book> list = null;
	        try {
	            list = DBUtils.queryBook(conn);
	        } catch (SQLException e) {
	        	System.out.println("To tutaj sie wywala");
	            e.printStackTrace();
	            errorString = e.getMessage();
	        }
	        // Store info in request attribute, before forward to views
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("booksList", list);
	         
	        RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/booksView.jsp");
	        dispatcher.forward(request, response);
	    }
	 
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }
	 
	}