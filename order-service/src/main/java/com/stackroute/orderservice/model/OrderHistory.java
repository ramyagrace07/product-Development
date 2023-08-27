package com.stackroute.orderservice.model;

import com.stackroute.orderservice.model.Product;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class OrderHistory {
    @Id
    String emailId;
    List<Product> products;
}
