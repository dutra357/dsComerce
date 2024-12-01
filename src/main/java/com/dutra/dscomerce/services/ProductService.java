package com.dutra.dscomerce.services;

import com.dutra.dscomerce.dtos.CategoryDto;
import com.dutra.dscomerce.dtos.ProducMinDto;
import com.dutra.dscomerce.dtos.ProductDto;
import com.dutra.dscomerce.entities.CategoryEntity;
import com.dutra.dscomerce.entities.ProductEntity;
import com.dutra.dscomerce.repositories.CategoryRepository;
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
    private final CategoryRepository categoryRepository;
    public ProductService(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        return new ProductDto(repository.findProductWithCategories(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado.")
        ));
    }

    @Override
    public Page<ProducMinDto> searchByName(Pageable pageable, String name) {
        return repository.searchByName(pageable, name).map(ProducMinDto::new);
    }

    @Override
    @Transactional
    public ProductDto saveProduct(ProductDto productEntry) {
        ProductEntity newProduct = new ProductEntity();
        builderProduct(newProduct, productEntry);

        return new ProductDto(repository.save(newProduct));
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, ProductDto product) {
        try {
            ProductEntity updatedProduct = repository.getReferenceById(id);

            builderProduct(updatedProduct, product);

            return new ProductDto(repository.save(updatedProduct));
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

    private ProductEntity builderProduct(ProductEntity product, ProductDto entry) {

        product.setImgUrl(entry.getImgUrl());
        product.setPrice(entry.getPrice());
        product.setName(entry.getName());
        product.setDescription(entry.getDescription());

        product.getCategories().clear();
        for (CategoryDto category : entry.getCategories()) {
            CategoryEntity cat = categoryRepository.getReferenceById(category.getId());
            product.getCategories().add(cat);
        }

        return product;
    }
}
