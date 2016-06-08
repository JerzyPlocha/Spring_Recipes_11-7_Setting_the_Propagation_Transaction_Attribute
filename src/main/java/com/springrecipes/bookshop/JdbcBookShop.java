package com.springrecipes.bookshop;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: Jerzy Plocha on 07/06/16.
 */
public class JdbcBookShop extends JdbcDaoSupport implements BookShop {

    // purchase operation is 3 operations:
    // 1-find price, 2-decrement book stock value, 3-debit price value from account
    // it is possible to deduct from BOOK_STOCK 1 item but not debit the value from ACCOUNT because this 3 operations are not wrapped into a single transaction
    // Propagation.REQUIRES_NEW will suspend transaction started by checkout() and start a new transaction
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void purchase(final String isbn, final String username) {

        int price = getJdbcTemplate().queryForObject(
                "SELECT PRICE FROM BOOK WHERE ISBN = ?", new Object[]{isbn}, Integer.class);

        getJdbcTemplate().update(
                "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", new Object[]{isbn});

        getJdbcTemplate().update(
                "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", new Object[]{price, username});

    }
}
