package org.tin.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

@WebServlet(urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public RegisterServlet() {
		super();
	}
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher 
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registerView.jsp");
 
        dispatcher.forward(request, response);
 
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String tmp_password = request.getParameter("password");

        MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] hashInBytes = md.digest(tmp_password.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
	        for (byte b : hashInBytes) {
	            sb.append(String.format("%02x", b));
	        }
	        tmp_password=sb.toString();
	        System.out.println(sb.toString());
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			System.out.println("Error during MD5 password");
		}
        
		String password=request.getParameter("password");
        //request.getParameter("password");
 
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
        	password = tmp_password;
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find the user in the DB.
                user = DBUtils.createUser(conn, userName, password);
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = "User already exists in data base!";//e.getMessage();
            }
        }
        // If error, forward to /WEB-INF/views/login.jsp
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
 
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registerView.jsp");
 
            dispatcher.forward(request, response);
        }
    }
}
