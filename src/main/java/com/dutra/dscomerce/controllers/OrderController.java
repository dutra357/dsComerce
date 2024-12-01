package com.dutra.dscomerce.controllers;

import com.dutra.dscomerce.dtos.ProducMinDto;
import com.dutra.dscomerce.dtos.ProductDto;
import com.dutra.dscomerce.dtos.orders.OrderDto;
import com.dutra.dscomerce.services.interfaces.ProductServiceInterface;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService service;
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

}
