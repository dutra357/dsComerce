package com.dutra.dscomerce.dtos;

import com.dutra.dscomerce.entities.CategoryEntity;
import com.dutra.dscomerce.entities.ProductEntity;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

public class ProductDto {

    private Long id;
    @Size(min = 3, max = 80, message = "Campo reclama de 3 a 80 caracteres.")
    @NotBlank(message = "Campo obrigatório.")
    private String name;
    @Size(min = 10, message = "Campo reclama 10 caracteres.")
    @NotBlank(message = "Campo obrigatório.")
    private String description;
    @Positive(message = "Campo exige valor positivo.")
    @NotNull(message = "Campo obrigatório.")
    private Double price;
    @NotBlank(message = "Campo obrigatório.")
    private String imgUrl;
    @NotEmpty(message = "Deve ter ao menos uma categoria.")
    private List<CategoryDto> categories = new ArrayList<>();

    public ProductDto() {}
    public ProductDto(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDto(ProductEntity product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();

        for (CategoryEntity category : product.getCategories()) {
            categories.add(new CategoryDto(category));
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

}
