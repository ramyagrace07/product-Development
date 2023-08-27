package com.stackroute.orderservice.service;

import com.stackroute.orderservice.Repository.OrderHistoryRepository;
import com.stackroute.orderservice.exception.EmailIdNotFoundException;
import com.stackroute.orderservice.model.OrderHistory;
import com.stackroute.orderservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderHistoryServiceDaoImpl implements OrderHistoryServiceDao{
    @Autowired
    OrderHistoryRepository repository;


    @Override
    public OrderHistory addOrderHistory(OrderHistory orders) {
        Optional<OrderHistory> orderHistory = repository.findById(orders.getEmailId());
        if(orderHistory.isEmpty()){
            return repository.save(orders);
        }
        else
        {
            OrderHistory orders1 =  orderHistory.get();
            List<Product> existProducts = orders1.getProducts();
            List<Product> newProducts = orders.getProducts();
            existProducts.addAll(newProducts);
            orders1.setProducts(existProducts);
            return    repository.save(orders1);
        }
    }

    @Override
    public List<Product> getProudctsByEmailId(String emailId) throws EmailIdNotFoundException {
        Optional<OrderHistory> order=repository.findById(emailId);
        if(order.isEmpty()){
            throw new EmailIdNotFoundException("emailId not found");
        }
        List<Product> list=order.get().getProducts();
        return list;
    }
}
