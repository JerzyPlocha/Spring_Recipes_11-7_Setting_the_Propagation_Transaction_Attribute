package com.springrecipes.bookshop;

import java.util.List;

/**
 * Author: Jerzy Plocha on 08/06/16.
 */
public interface Cashier {

    public void checkout(List<String> isbns, String username);
}
