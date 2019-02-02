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
 
        String sql = "Select a.User_Name, a.Password, a.Gender from User_Accounts a " //
                + " where a.User_Name = ? and a.password= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String gender = rs.getString("Gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }
 
    public static UserAccount findUser(Connection conn, String userName) throws SQLException {
 
        String sql = "Select a.User_Name, a.Password, a.Gender from User_Accounts a "//
                + " where a.User_Name = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String password = rs.getString("Password");
            String gender = rs.getString("Gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }
 
    public static List<Book> queryBook(Connection conn) throws SQLException {
        String sql = "Select a.title, a.author, a.ISBN from Books a ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Book> list = new ArrayList<Book>();
        while (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("author");
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
        String sql = "Select a.title, a.author, a.ISBN from Books a where a.ISBN=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, ISBN);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	String title  = rs.getString("title");
            String author = rs.getString("author");
            Book book = new Book(title, author,ISBN);
            return book;
        }
        return null;
    }
 
    public static void updateBook(Connection conn, Book book) throws SQLException {
        String sql = "Update Books set title =?, author=? where ISBN=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, book.getTitle());
        pstm.setString(2, book.getAuthor());
        pstm.setString(3, book.getISBN());
        pstm.executeUpdate();
    }
 
    public static void insertBook(Connection conn, Book book) throws SQLException {
        String sql = "Insert into Books(title, author,ISBN) values (?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, book.getTitle());
        pstm.setString(2, book.getAuthor());
        pstm.setString(3, book.getISBN());
 
        pstm.executeUpdate();
    }
 
    public static void deleteBook(Connection conn, String ISBN) throws SQLException {
        String sql = "Delete From Books where ISBN= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, ISBN);
 
        pstm.executeUpdate();
    }
 
}