package com.stackroute.orderservice.Controller;

import com.stackroute.orderservice.exception.EmailIdNotFoundException;
import com.stackroute.orderservice.exception.ProuductIdNotFoundException;
import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.model.Product;
import com.stackroute.orderservice.service.CartServiceDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("cart")
@CrossOrigin("*")
public class CartController {
    @Autowired
    CartServiceDaoImpl service;

    @PostMapping("addCart")
    public ResponseEntity<?> addCart(@RequestBody Cart cart){

        Cart cart1= service.addCart(cart);
        return new ResponseEntity<Cart>(cart1, HttpStatus.CREATED);
    }


    @GetMapping("viewProductsByEmailId/{email}")
    public ResponseEntity<?> viewProductsByEmailId(@PathVariable("email") String emailId){
        try{
            List<Product> list=service.getCartByEmailId(emailId);
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }catch (EmailIdNotFoundException e){
            return new ResponseEntity<String >("emailId not found here", HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping("deleteByProductId/{emailId}/{productId}")
    public ResponseEntity<?> deleteProducts(@PathVariable String emailId, @PathVariable String productId){
        try {
            boolean result = service.deleteByProductId(emailId,productId);
            return  new ResponseEntity<String>("product is Deleted",HttpStatus.OK);
        } catch (EmailIdNotFoundException e) {
            return new ResponseEntity<String>("INvalid emaiI id",HttpStatus.NOT_FOUND);
        } catch (ProuductIdNotFoundException e) {
            return new ResponseEntity<String>("ProductId id not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deletebyEmailId/{emailId}")
    public ResponseEntity<?> deleteCartByID(@PathVariable String emailId){
        try {
            boolean result = service.deleteCartById(emailId);
            return new ResponseEntity<String>("Cart Deleted",HttpStatus.OK);
        } catch (EmailIdNotFoundException e) {
            return new ResponseEntity<String>("emailId not Found in DB",HttpStatus.NOT_FOUND);
        }
    }

}

