package com.dutra.dscomerce.services;

import com.dutra.dscomerce.dtos.orders.ItemDto;
import com.dutra.dscomerce.dtos.orders.OrderDto;
import com.dutra.dscomerce.entities.OrderEntity;
import com.dutra.dscomerce.entities.OrderItemEntity;
import com.dutra.dscomerce.entities.ProductEntity;
import com.dutra.dscomerce.entities.UserEntity;
import com.dutra.dscomerce.enums.OrderStatus;
import com.dutra.dscomerce.repositories.OrderItemRepository;
import com.dutra.dscomerce.repositories.OrderRepository;
import com.dutra.dscomerce.services.exceptions.ForbidenException;
import com.dutra.dscomerce.services.exceptions.ResourceNotFoundException;
import com.dutra.dscomerce.services.interfaces.OrderServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService implements OrderServiceInterface {

    private final AuthService authService;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository repository;
    private final UserService userService;
    private final ProductService productService;
    public OrderService(AuthService authService, OrderItemRepository orderItemRepository, OrderRepository repository, UserService userService, ProductService productService) {
        this.authService = authService;
        this.orderItemRepository = orderItemRepository;
        this.repository = repository;
        this.userService = userService;
        this.productService = productService;
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDto findById(Long id) {

        OrderEntity order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado.")
        );

        authService.validationSelfOrAdmin(order.getClient().getId());

        return new OrderDto(order);
    }

    @Transactional
    @Override
    public OrderDto saveOrder(OrderDto newOrder) {
        OrderEntity order = new OrderEntity();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        UserEntity user = userService.getAuthenticated();
        order.setClient(user);

        for (ItemDto item : newOrder.getItems()) {
            ProductEntity product = productService.getReferenceById(item.getProductId());
            OrderItemEntity orderItem = new OrderItemEntity(order, product, item.getQuantity(), product.getPrice());
            order.getItems().add(orderItem);
        }

        repository.save(order);

        orderItemRepository.saveAll(order.getItems());

        return new OrderDto(order);
    }
}
