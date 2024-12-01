package com.dutra.dscomerce.services.interfaces;


import com.dutra.dscomerce.dtos.orders.OrderDto;


public interface OrderServiceInterface {

    OrderDto findById(Long id);

    OrderDto saveOrder(OrderDto neworder);

}
