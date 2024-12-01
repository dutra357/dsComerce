package com.dutra.dscomerce.dtos.orders;

import com.dutra.dscomerce.entities.PaymentEntity;

import java.time.Instant;

public class PaymentDto {

    private Long id;
    private Instant moment;

    public PaymentDto(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDto(PaymentEntity entity) {
        id = entity.getId();
        moment = entity.getMoment();
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }
}
