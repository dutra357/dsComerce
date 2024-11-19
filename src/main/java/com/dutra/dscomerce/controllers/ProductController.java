package com.dutra.dscomerce.controllers;

import com.dutra.dscomerce.dtos.ProductDto;
import com.dutra.dscomerce.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ProductDto test() {
        return service.findById(1L);
    }
}
