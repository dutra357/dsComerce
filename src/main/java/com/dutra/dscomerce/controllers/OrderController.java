package com.dutra.dscomerce.controllers;

import com.dutra.dscomerce.dtos.orders.OrderDto;
import com.dutra.dscomerce.services.interfaces.OrderServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderServiceInterface service;
    public OrderController(OrderServiceInterface service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<OrderDto> saveProduct(@Valid @RequestBody OrderDto newOrder) {
        OrderDto orderSaved = service.saveOrder(newOrder);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(orderSaved.getId()).toUri();

        return ResponseEntity.created(uri).body(orderSaved);
    }

}
