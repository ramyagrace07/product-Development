package com.stackroute.productservice.repository;
import com.stackroute.productservice.model.Product;

import jdk.jfr.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductRepository extends MongoRepository<Product, String > {
    public List<Product> findByProductCategory(String productCategory);
    public List<Product> findByProductSubCategory(String productSubCategory);
    public List<Product> findByPriceGreaterThan(int price);
    public List<Product> findByProductCategoryOrderByPrice(String productCategory);
    public List<Product> findByProductCategoryOrderByPriceDesc(String productCategory);
    public List<Product> findByProductCategoryOrderByRating(String  proudctCategory);
    public List<Product> findByProductCategoryOrderByRatingDesc(String  proudctCategory);
    public List<Product> findBySellerEmailId(String sellerEmailId);
}
