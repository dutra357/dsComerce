package com.dutra.dscomerce.services;

import com.dutra.dscomerce.dtos.ProductDto;
import com.dutra.dscomerce.dtos.ProductEntry;
import com.dutra.dscomerce.entities.ProductEntity;
import com.dutra.dscomerce.repositories.ProductRepository;
import com.dutra.dscomerce.services.interfaces.ProductServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService implements ProductServiceInterface {

    private final ProductRepository repository;
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        return builderDto(repository.findById(id).get());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(product -> builderDto(product));
    }

    @Override
    @Transactional
    public ProductDto saveProduct(ProductEntry product) {
        ProductEntity newProduct = new ProductEntity();

        newProduct.setName(product.name());
        newProduct.setDescription(product.description());
        newProduct.setPrice(product.price());
        newProduct.setImgUrl(product.imgUrl());

        return builderDto(repository.save(newProduct));
    }

    private ProductDto builderDto(ProductEntity product) {
        return new ProductDto(product.getId(),
                product.getName(), product.getDescription(),
                product.getPrice(), product.getImgUrl());
    }
}
