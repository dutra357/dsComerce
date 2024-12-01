package com.dutra.dscomerce.controllers;

import com.dutra.dscomerce.dtos.ProducMinDto;
import com.dutra.dscomerce.dtos.ProductDto;
import com.dutra.dscomerce.services.interfaces.ProductServiceInterface;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProducMinDto>> searchByName(Pageable pageable, @RequestParam(name = "name", defaultValue = "") String name) {
        return ResponseEntity.ok(service.searchByName(pageable, name));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto product) {
        ProductDto newProduct = service.saveProduct(product);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(newProduct.getId()).toUri();

        return ResponseEntity.created(uri).body(newProduct);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto product) {
        ProductDto updateProduct = service.updateProduct(id, product);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("").buildAndExpand(updateProduct.getId()).toUri();

        return ResponseEntity.created(uri).body(updateProduct);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
