package com.dutra.dscomerce.controllers;

import com.dutra.dscomerce.dtos.ProductDto;
import com.dutra.dscomerce.dtos.ProductEntry;
import com.dutra.dscomerce.services.interfaces.ProductServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductServiceInterface service;

    public ProductController(ProductServiceInterface service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Page<ProductDto> findAll(Pageable pageable ) {
        return service.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductEntry product) {
        ProductDto newProduct = service.saveProduct(product);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(newProduct.id()).toUri();

        return ResponseEntity.created(uri).body(newProduct);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductEntry product) {
        ProductDto updateProduct = service.updateProduct(id, product);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("").buildAndExpand(updateProduct.id()).toUri();

        return ResponseEntity.created(uri).body(updateProduct);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
