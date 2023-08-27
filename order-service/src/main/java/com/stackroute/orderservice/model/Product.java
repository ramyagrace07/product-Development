package com.stackroute.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product {

    @Id
    private String productId ;
    private String productName;
    private String productCategory;
    private String productSubCategory;
    private String productBrand;
    private byte[] productImage;
    private String productDesc;
    private int stockQuantity;
    private int price;
    private int rating;
    private int sellingPrice;
    private String sellerEmailId;

}

