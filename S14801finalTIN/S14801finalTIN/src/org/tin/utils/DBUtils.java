package org.tin.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.tin.beans.Book;
import org.tin.beans.UserAccount;
 
public class DBUtils {
 
    public static UserAccount findUser(Connection conn, //
            String userName, String password) throws SQLException {
 
        String sql = "Select a.User_Name, a.Password, a.Gender, b.resp_name from User_Accounts a, User_resp b  where a.resp_lvl=b.resp_lvl and a.User_Name = ? and a.Password= ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        System.out.println("2");
        if (rs.next()) {
            String gender = rs.getString("Gender");
            String respName=rs.getString("resp_name");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            user.setRespName(respName);
            return user;
        }
        return null;
    }
 
    public static UserAccount findUser(Connection conn, String userName) throws SQLException {
 
        String sql = "Select a.User_Name, a.Password, a.Gender, b.resp_name from User_Accounts a, User_resp b " //
        + " where a.resp_lvl=b.resp_lvl and a.User_Name = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String password = rs.getString("Password");
            String gender = rs.getString("Gender");
            String respName=rs.getString("respName");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            user.setRespName(respName);
            return user;
        }
        return null;
    }
    
    public static UserAccount createUser(Connection conn, String userName, String password) throws SQLException {
    	 
        String sql = "select a.user_name from user_accounts a where a.user_name=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        ResultSet rs = pstm.executeQuery();
        
        //System.out.println("1");
        if (rs.next())           
            return null;
        
        
        sql = "insert into user_accounts (USER_NAME, GENDER, PASSWORD, RESP_LVL) values (?, 'M', ?,2)";
        
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        pstm.executeUpdate();
        
        sql = "Select a.User_Name, a.Password, a.Gender, b.resp_name from User_Accounts a, User_resp b  where a.resp_lvl=b.resp_lvl and a.User_Name = ?";
        
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        rs = pstm.executeQuery();
        
        if (rs.next()) {
            password = rs.getString("Password");
            String gender = rs.getString("Gender");
            String respName=rs.getString("resp_name");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            user.setRespName(respName);
            return user;
        }
        
        return null;
    }
 
    public static List<Book> queryBook(Connection conn) throws SQLException {
        String sql = "select a.title, GET_BOOK_AUTHORS(A.ISBN) name, a.ISBN from books a where a.isbn=(select max(ab.isbn) from books_authors ab where ab.isbn=a.isbn)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Book> list = new ArrayList<Book>();
        while (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("name");
            String ISBN = rs.getString("ISBN");
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setISBN(ISBN);
            list.add(book);
        }
        return list;
    }
 
    public static Book findBook(Connection conn, String ISBN) throws SQLException {
        String sql = "select a.title, GET_BOOK_AUTHORS(A.ISBN) name, a.ISBN from books a where a.isbn=(select max(ab.isbn) from books_authors ab where ab.isbn=a.isbn) and a.ISBN=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, ISBN);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	String title  = rs.getString("title");
            String author = rs.getString("name");
            Book book = new Book(title, author,ISBN);
            return book;
        }
        return null;
    }
 
    public static void updateBook(Connection conn, Book book) throws SQLException {
    	//Nie mozna edytowac autora poniewaz
        String sql = "Update Books set title =? where ISBN=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, book.getTitle());
        pstm.setString(2, book.getISBN());
        pstm.executeUpdate();
    }
    
    public static void insertBook(Connection conn, Book book) throws SQLException {
    	//Also creating new Author in case when there is no such
    	
    	String sql =" select b.author_id, b.name from authors b where b.name=? ";
    	
    	PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, book.getAuthor());
 
        ResultSet rs = pstm.executeQuery();
        
        String author_id="";
        String name=book.getAuthor();
        
        if (rs.next()){
        	author_id  = rs.getString("author_id");
            name = rs.getString("name");
	        while (rs.next()) {
	        	author_id  = rs.getString("author_id");
	            name = rs.getString("name");
	        }
        }else{
        	String sqlIdentifier = "select author_seq.NEXTVAL from dual";
        	PreparedStatement pst = conn.prepareStatement(sqlIdentifier);
        	pst.executeQuery();
        	ResultSet rsSeq = pst.executeQuery();
        	
        	if(rsSeq.next())
        	   author_id= rsSeq.getString("NEXTVAL");
        	
        	sql="insert into AUTHORS (AUTHOR_ID, NAME) values (?,?)";
        	pstm = conn.prepareStatement(sql);
        	pstm.setString(1, author_id);
            pstm.setString(2, name);
 
            pstm.executeUpdate();
        }
        
        //sprawdzenie czy juz istnieje ksiazka
        sql = "select a.title from books a where a.isbn=?";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, book.getISBN());
    	rs = pstm.executeQuery();
        
    	if(rs.next()) {
    		System.out.println("Tylko wstawienie powiazania");
    		sql="insert into BOOKS_AUTHORS (AUTHOR_ID, ISBN) values (?, ?)";
    		pstm = conn.prepareStatement(sql);
            pstm.setString(1, author_id);
            pstm.setString(2, book.getISBN());
            pstm.executeUpdate();
            
    	}else {
    		System.out.println("Wstawienie powiazania i ksiazki");
	    	//Wstawienie kilku takich samych ksiazek to bedzie dodanie kilku autorow do ksiazek
	        sql = "Insert into Books(title, ISBN) values (?,?)";
	        pstm = conn.prepareStatement(sql);
	        
	        
	        
	        pstm = conn.prepareStatement(sql);
	 
	        pstm.setString(1, book.getTitle());
	        pstm.setString(2, book.getISBN());
	 
	        pstm.executeUpdate();
	        
	        sql="insert into BOOKS_AUTHORS (AUTHOR_ID, ISBN) values (?, ?)";
	        pstm = conn.prepareStatement(sql);
	        pstm.setString(1, author_id);
	        pstm.setString(2, book.getISBN());
	        pstm.executeUpdate();
	        
    	}
    }
 
    public static void deleteBook(Connection conn, String ISBN) throws SQLException {
        String sql = "Delete From Books where ISBN= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, ISBN);
 
        pstm.executeUpdate();
    }
 
}