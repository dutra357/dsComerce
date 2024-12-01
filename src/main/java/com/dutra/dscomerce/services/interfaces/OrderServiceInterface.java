package com.dutra.dscomerce.services.interfaces;


import com.dutra.dscomerce.dtos.orders.OrderDto;

import java.util.Optional;

public interface OrderServiceInterface {

    OrderDto findById(Long id);

}
