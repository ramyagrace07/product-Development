package com.stackroute.productservice.service;


import com.stackroute.productservice.exception.ProductAlreadyExistException;
import com.stackroute.productservice.exception.ProductIdNotFoundException;
import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceDaoImpl implements  ProductServiceDao{
    @Autowired
    ProductRepository repository;
    @Override
    public  Product addProduct(Product product) throws ProductAlreadyExistException {
        Product product1;
        Optional<Product> optionalProduct=repository.findById(product.getProductId());
        if(optionalProduct.isPresent()){
            throw new ProductAlreadyExistException("ProductId already Exist");
        }else
            product1=repository.save(product);
        return product1;
    }

    @Override
    public List<Product> viewall() {
        return  repository.findAll();
    }

    @Override
    public Product getProductByProductId(String productId) throws ProductIdNotFoundException {
        Product product1;
        Optional<Product> optionalProduct=repository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ProductIdNotFoundException("product id not exist");
        }else
            return optionalProduct.get();

    }

    @Override
    public List<Product> getProductsByCategory(String productCategory) {
        return repository.findByProductCategory(productCategory);
    }


    @Override
    public List<Product> getProductsBySubCategory(String productSubCategory) {
        return repository.findByProductSubCategory(productSubCategory);
    }

    @Override
    public List<Product> getProductsGreaterThanPrice(int price) {
        return repository.findByPriceGreaterThan(price);
    }

    @Override
    public List<Product> getProductByProductCategoryOrderByPrice(String productCatgory) {
        return repository.findByProductCategoryOrderByPrice(productCatgory);
    }
    @Override
    public List<Product> getProductByProductCategoryOrderByPriceDesc(String productCatgory) {
        return repository.findByProductCategoryOrderByPriceDesc(productCatgory);
    }

    @Override
    public List<Product> getProductByProductCategoryOrderByRating(String productCategory) {
        return repository.findByProductCategoryOrderByRating(productCategory);
    }
    @Override
    public List<Product> getProductByProductCategoryOrderByRatingDesc(String productCategory) {
        return repository.findByProductCategoryOrderByRatingDesc(productCategory);
    }

    @Override
    public List<Product> getProductBySellerEmaildId(String sellerEamilId) {
        return repository.findBySellerEmailId(sellerEamilId);
    }

    @Override
    public boolean deleteById(String productId) throws ProductIdNotFoundException {
        Optional<Product> product=repository.findById(productId);
        if(product.isEmpty()){
            throw  new ProductIdNotFoundException("product ID doesn't exist");
        }else{
            repository.deleteById(productId);
            return true;
        }
    }


}