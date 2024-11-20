package com.dutra.dscomerce.services;

import com.dutra.dscomerce.dtos.ProductDto;
import com.dutra.dscomerce.dtos.ProductEntry;
import com.dutra.dscomerce.entities.ProductEntity;
import com.dutra.dscomerce.repositories.ProductRepository;
import com.dutra.dscomerce.services.exceptions.DatabaseException;
import com.dutra.dscomerce.services.exceptions.ResourceNotFoundException;
import com.dutra.dscomerce.services.interfaces.ProductServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
        return builderDto(repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado.")
        ));
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
        try {
            ProductEntity updatedProduct = repository.getReferenceById(id);

            updatedProduct.setImgUrl(product.imgUrl());
            updatedProduct.setPrice(product.price());
            updatedProduct.setName(product.name());
            updatedProduct.setDescription(product.description());

            return builderDto(repository.save(updatedProduct));
        } catch (EntityNotFoundException exception) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade referencial / FK");
        }
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
