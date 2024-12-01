package com.dutra.dscomerce.services;

import com.dutra.dscomerce.dtos.CategoryDto;
import com.dutra.dscomerce.repositories.CategoryRepository;
import com.dutra.dscomerce.services.interfaces.CategoryInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService implements CategoryInterface {

    private final CategoryRepository repository;
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDto> findAll() {
        return repository.findAll().stream().map(category -> new CategoryDto(category)).toList();
    }

}
