package com.example.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private record Product(int id,
                          String name,
                          double price) {
    }

    List<Product> products = new ArrayList<>(

            List.of(
                    new Product(1, "car1", 99.08),
                    new Product(2, "car2", 66.05),
                    new Product(3, "car3", 96.36)
            )
    );


    @GetMapping
    public List<Product> getProducts() {
        return products;

    }

}