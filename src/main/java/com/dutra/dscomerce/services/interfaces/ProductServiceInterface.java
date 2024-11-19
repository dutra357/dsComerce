package com.dutra.dscomerce.services.interfaces;

import com.dutra.dscomerce.dtos.ProductDto;

public interface ProductServiceInterface {

    ProductDto findById(Long id);
}
