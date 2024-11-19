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
        return builderDto(repository.save(builderProduct(product)));
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, ProductEntry product) {
        ProductEntity updatedProduct = repository.getReferenceById(id);

        updatedProduct.setImgUrl(product.imgUrl());
        updatedProduct.setPrice(product.price());
        updatedProduct.setName(product.name());
        updatedProduct.setDescription(product.description());

        return builderDto(repository.save(updatedProduct));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ProductDto builderDto(ProductEntity product) {
        return new ProductDto(product.getId(),
                product.getName(), product.getDescription(),
                product.getPrice(), product.getImgUrl());
    }

    private ProductEntity builderProduct(ProductEntry entry) {
        ProductEntity product = new ProductEntity();

        product.setImgUrl(entry.imgUrl());
        product.setPrice(entry.price());
        product.setName(entry.name());
        product.setDescription(entry.description());

        return product;
    }
}
