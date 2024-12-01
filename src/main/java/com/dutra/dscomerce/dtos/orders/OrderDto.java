package com.dutra.dscomerce.dtos.orders;

import com.dutra.dscomerce.entities.OrderEntity;
import com.dutra.dscomerce.entities.OrderItemEntity;
import com.dutra.dscomerce.enums.OrderStatus;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDto client;
    private PaymentDto payment;
    @NotEmpty(message = "Deve ter pelo menos um item")
    private List<ItemDto> items = new ArrayList<>();

    public OrderDto(Long id, Instant moment, OrderStatus status, ClientDto client, PaymentDto payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDto(OrderEntity entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
        this.status = entity.getStatus();
        this.client = new ClientDto(entity.getClient());
        this.payment = (entity.getPayment() == null) ? null : new PaymentDto(entity.getPayment());
        for (OrderItemEntity item : entity.getItems()) {
            ItemDto itemDto = new ItemDto(item);
            items.add(itemDto);
        }
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDto getClient() {
        return client;
    }

    public PaymentDto getPayment() {
        return payment;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public Double getTotal() {
        double sum = 0.0;
        for (ItemDto item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }
}
