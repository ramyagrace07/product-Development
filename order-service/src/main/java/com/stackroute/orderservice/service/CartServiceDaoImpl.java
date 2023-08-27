package com.stackroute.orderservice.service;

import com.stackroute.orderservice.Repository.CartRepository;
import com.stackroute.orderservice.exception.EmailIdNotFoundException;
import com.stackroute.orderservice.exception.ProuductIdNotFoundException;
import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceDaoImpl implements CartServiceDao{

    @Autowired
    CartRepository repository;
    @Override
    public Cart addCart(Cart cart) {
        Optional<Cart> cart1 = repository.findById(cart.getEmailId());
        if(cart1.isEmpty()){
            return repository.save(cart);
        }
        else
        {
            Cart cart2 =  cart1.get();
            List<Product> existProducts = cart2.getProducts();
            List<Product> newProducts = cart.getProducts();
            existProducts.addAll(newProducts);
            cart2.setProducts(existProducts);
            return repository.save(cart2);
        }

//        return repository.save(cart);
    }

    @Override
    public boolean deleteByProductId(String emailId, String productId) throws EmailIdNotFoundException, ProuductIdNotFoundException {
        Optional<Cart> cart = repository.findById(emailId);
        if(cart.isEmpty()){
            throw new EmailIdNotFoundException("Email Id not found");
        }
        else {
            Cart cart1 = cart.get();
            List<Product> existProducts = cart1.getProducts();
            System.out.println(existProducts);
            existProducts.removeIf((pro) ->pro.getProductId().equals(productId));
            cart1.setProducts(existProducts);
            repository.save(cart1);
            return true;
        }
    }

    @Override
    public boolean deleteCartById(String emailId) throws EmailIdNotFoundException {
        Optional<Cart> optionalCart = repository.findById(emailId);
        if(optionalCart.isPresent()){
            repository.deleteById(emailId);
        }
        else
            throw new EmailIdNotFoundException("EmailId not found Exception");
        return true;

    }

    @Override
    public List<Product> getCartByEmailId(String emailId) throws EmailIdNotFoundException{
        Optional<Cart> cart=repository.findById(emailId);
        if(cart.isEmpty()){
            throw new EmailIdNotFoundException("emailId not found");
        }
        List<Product> list=cart.get().getProducts();
        return list;
    }




}

