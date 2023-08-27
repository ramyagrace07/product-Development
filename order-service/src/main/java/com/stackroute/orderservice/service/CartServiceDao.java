package com.stackroute.orderservice.service;

import com.stackroute.orderservice.exception.EmailIdNotFoundException;
import com.stackroute.orderservice.exception.ProuductIdNotFoundException;
import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.model.Product;

import java.util.List;

public interface CartServiceDao {
    public Cart addCart(Cart cart);
    public List<Product> getCartByEmailId(String emailId) throws EmailIdNotFoundException;
    boolean deleteByProductId(String emailId, String productId) throws EmailIdNotFoundException, ProuductIdNotFoundException;
    boolean deleteCartById(String emailId) throws EmailIdNotFoundException;
}
