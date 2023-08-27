package com.stackroute.orderservice.service;

import com.stackroute.orderservice.exception.EmailIdNotFoundException;
import com.stackroute.orderservice.model.OrderHistory;
import com.stackroute.orderservice.model.Product;

import java.util.List;

public interface OrderHistoryServiceDao {

    //    public Product addProduct(Product product) throws ProductAlreadyExistException;
    public OrderHistory addOrderHistory(OrderHistory orders);
    public List<Product> getProudctsByEmailId(String emailId) throws EmailIdNotFoundException;

//    public  getProducts( );

}
