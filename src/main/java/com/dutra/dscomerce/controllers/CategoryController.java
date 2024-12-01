package com.dutra.dscomerce.controllers;

import com.dutra.dscomerce.dtos.CategoryDto;
import com.dutra.dscomerce.services.interfaces.CategoryInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryInterface service;
    public CategoryController(CategoryInterface service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> searchByName() {
        return ResponseEntity.ok(service.findAll());
    }
}
