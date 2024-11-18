package com.dutra.dscomerce.entities;

import com.dutra.dscomerce.enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    private OrderStatus status;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaymentEntity payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserEntity client;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItemEntity> items = new HashSet<OrderItemEntity>();

    public OrderEntity() {}

    public OrderEntity(Long id, Instant moment, OrderStatus status, PaymentEntity payment, UserEntity client) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.payment = payment;
        this.client = client;
    }

    public Set<OrderItemEntity> getItems() {
        return items;
    }

    public List<ProductEntity> getProducts() {
        return items.stream().map(OrderItemEntity::getProduct).toList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public UserEntity getClient() {
        return client;
    }

    public void setClient(UserEntity client) {
        this.client = client;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", moment=" + moment +
                ", status=" + status +
                ", client=" + client +
                '}';
    }
}
