package com.dutra.dscomerce.dtos;

import com.dutra.dscomerce.entities.CategoryEntity;

public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto() {}
    public CategoryDto(CategoryEntity category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
