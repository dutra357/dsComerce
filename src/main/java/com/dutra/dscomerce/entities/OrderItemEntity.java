package com.dutra.dscomerce.entities;

import com.dutra.dscomerce.entities.embedPk.OrderItemPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderItemEntity implements Serializable {

    @EmbeddedId
    private OrderItemPk id = new OrderItemPk();

    private Integer quantity;

    private Double price;

    public OrderItemEntity() {}
    public OrderItemEntity(OrderEntity order, ProductEntity product, Integer quantity, Double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderEntity getOrder() {
        return id.getOrder();
    }

    public void serOrder(OrderEntity order) {
        id.setOrder(order);
    }

    public ProductEntity getProduct() {
        return id.getProduct();
    }

    public void setProduct(ProductEntity product) {
        id.setProduct(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntity that = (OrderItemEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
