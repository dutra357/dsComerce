package com.dutra.dscomerce.services.interfaces;

import com.dutra.dscomerce.dtos.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductServiceInterface {

    ProductDto findById(Long id);

    Page<ProductDto> findAll(Pageable pageable);
}
