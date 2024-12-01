package com.dutra.dscomerce.dtos.orders;

import com.dutra.dscomerce.entities.OrderEntity;
import com.dutra.dscomerce.enums.OrderStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDto client;
    private PaymentDto payment;
    private List<ItemDto> items = new ArrayList<ItemDto>();

    public OrderDto() {}
    public OrderDto(Long id, Instant moment, OrderStatus status, ClientDto client, PaymentDto payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDto(OrderEntity order) {
        this.id = order.getId();
        this.moment = order.getMoment();
        this.status = order.getStatus();
        this.client = new ClientDto(order.getClient());
        this.payment = (order.getPayment() == null) ? null : new PaymentDto(order.getPayment());
    }

    public Double getTotal() {
        Double sum = 0.0;
        for (ItemDto item : this.items) {
            sum += item.getSubTotal();
        }
        return sum;
    }


}
