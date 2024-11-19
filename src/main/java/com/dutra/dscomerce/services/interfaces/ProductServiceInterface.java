package com.dutra.dscomerce.services.interfaces;

import com.dutra.dscomerce.dtos.ProductDto;
import com.dutra.dscomerce.dtos.ProductEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductServiceInterface {

    ProductDto findById(Long id);

    Page<ProductDto> findAll(Pageable pageable);

    ProductDto saveProduct(ProductEntry product);

    ProductDto updateProduct(Long id, ProductEntry product);

    void delete(Long id);
}
