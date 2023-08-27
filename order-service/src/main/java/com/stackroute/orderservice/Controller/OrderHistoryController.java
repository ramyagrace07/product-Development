package com.stackroute.orderservice.Controller;

import com.stackroute.orderservice.exception.EmailIdNotFoundException;
import com.stackroute.orderservice.model.OrderHistory;
import com.stackroute.orderservice.model.Product;
import com.stackroute.orderservice.service.OrderHistoryServiceDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("order")
@CrossOrigin("*")
public class OrderHistoryController {
    @Autowired
    OrderHistoryServiceDaoImpl service;

    @PostMapping("add")
    public ResponseEntity<?> addOrder(@RequestBody OrderHistory order){

        OrderHistory orderHistory = service.addOrderHistory(order);
        return new ResponseEntity<OrderHistory>(orderHistory, HttpStatus.CREATED);
    }

    @GetMapping("viewProductsByEmailId/{email}")
    public ResponseEntity<?> viewProductsByEmailId(@PathVariable("email") String emailId){
        try{
            List<Product> list=service.getProudctsByEmailId(emailId);
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }catch (EmailIdNotFoundException e){
            return new ResponseEntity<String >("emailId not found here", HttpStatus.CONFLICT);
        }
    }
}