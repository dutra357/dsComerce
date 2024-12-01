package com.dutra.dscomerce.services.interfaces;

import com.dutra.dscomerce.dtos.ProducMinDto;
import com.dutra.dscomerce.dtos.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductServiceInterface {

    ProductDto findById(Long id);

    Page<ProducMinDto> searchByName(Pageable pageable, String name);

    ProductDto saveProduct(ProductDto product);

    ProductDto updateProduct(Long id, ProductDto product);

    void delete(Long id);
}
