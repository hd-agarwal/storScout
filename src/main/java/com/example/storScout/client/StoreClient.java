package com.example.storScout.client;

import com.example.storScout.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@Service
@HttpExchange(url = "/products")
public interface StoreClient {
    @GetExchange
    List<Product> getAllProducts();

    @GetExchange("/{id}")
    Product getProductById(@PathVariable int id);

    @PostExchange
    Product addProduct(@RequestBody Product product);
}
