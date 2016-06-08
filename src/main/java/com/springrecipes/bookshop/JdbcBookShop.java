package com.springrecipes.bookshop;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Jerzy Plocha on 07/06/16.
 */
public class JdbcBookShop implements BookShop {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    // purchase operation is 3 operations:
    // 1-find price, 2-decrement book stock value, 3-debit price value from account
    // it is possible to deduct from BOOK_STOCK 1 item but not debit the value from ACCOUNT because this 3 operations are not wrapped into a single transaction
    @Override
    public void purchase(String isbn, String username) {

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            // 1
            PreparedStatement stmt1 = conn.prepareStatement("SELECT PRICE FROM BOOK WHERE ISBN = ?");
            stmt1.setString(1, isbn);
            ResultSet rs = stmt1.executeQuery();
            rs.next();
            int price = rs.getInt("PRICE");
            System.out.println("this book is: " + price);
            stmt1.close();

            // 2
            PreparedStatement stmt2 = conn.prepareStatement("UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?");
            stmt2.setString(1, isbn);
            System.out.println("decreasing stock for isbn: " + isbn);
            stmt2.executeUpdate();
            stmt2.close();

            // 3
            PreparedStatement stmt3 = conn.prepareStatement("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?");
            stmt3.setInt(1, price);
            System.out.println("decreasing balance for user: " + username + ", " + price);
            stmt3.setString(2, username);
            stmt3.executeUpdate();
            stmt3.close();

            conn.commit();

        } catch (SQLException e) {
//            e.printStackTrace();
            if(conn != null){
                try {
                    System.out.println("rolling back...");
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            throw new RuntimeException(e);
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
