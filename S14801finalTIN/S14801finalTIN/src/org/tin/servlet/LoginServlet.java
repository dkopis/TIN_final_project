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
 
@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
    }
 
    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher 
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
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
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
 
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find the user in the DB.
            	System.out.println("1");
            	 password = tmp_password;
                user = DBUtils.findUser(conn, userName, password);
                
                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
            System.out.print("2a");
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
            dispatcher.forward(request, response);
        }
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
 
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            else {
                MyUtils.deleteUserCookie(response);
            }
 
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
 
}