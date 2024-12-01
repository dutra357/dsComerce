package com.dutra.dscomerce.services.interfaces;

import com.dutra.dscomerce.dtos.CategoryDto;

import java.util.List;

public interface CategoryInterface {

    List<CategoryDto> findAll();
}
