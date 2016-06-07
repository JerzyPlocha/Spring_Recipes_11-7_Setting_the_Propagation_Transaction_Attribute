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

    @Override
    public void purchase(String isbn, String username) {

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt1 = conn.prepareStatement("SELECT BOOK_NAME FROM BOOK WHERE ISBN = ?");
            stmt1.setString(1, isbn);
            ResultSet rs = stmt1.executeQuery();
            rs.next();
            String name = rs.getString("BOOK_NAME");
            System.out.println(name);
            stmt1.close();
        } catch (SQLException e) {
//            e.printStackTrace();
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
