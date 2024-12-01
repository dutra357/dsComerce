package com.dutra.dscomerce.controllers;

import com.dutra.dscomerce.dtos.orders.OrderDto;
import com.dutra.dscomerce.services.interfaces.OrderServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderServiceInterface service;
    public OrderController(OrderServiceInterface service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

}
