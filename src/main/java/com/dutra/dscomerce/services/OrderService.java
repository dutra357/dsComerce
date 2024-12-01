package com.dutra.dscomerce.services;

import com.dutra.dscomerce.dtos.orders.OrderDto;
import com.dutra.dscomerce.entities.OrderEntity;
import com.dutra.dscomerce.repositories.OrderRepository;
import com.dutra.dscomerce.services.exceptions.ResourceNotFoundException;
import com.dutra.dscomerce.services.interfaces.OrderServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderService implements OrderServiceInterface {

    private final OrderRepository repository;
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDto findById(Long id) {
        OrderEntity order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado.")
        );

        return new OrderDto(order);
    }
}
