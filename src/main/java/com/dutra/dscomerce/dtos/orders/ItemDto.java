package com.dutra.dscomerce.dtos.orders;

import com.dutra.dscomerce.entities.OrderItemEntity;

public class ItemDto {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;

    public ItemDto() {}
    public ItemDto(Long productId, String name, Double price, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ItemDto(OrderItemEntity item) {
        this.productId = item.getProduct().getId();
        this.name = item.getProduct().getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
    }

    public Double getSubTotal() {
        return this.price * this.quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
