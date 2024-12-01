package com.dutra.dscomerce.repositories;

import com.dutra.dscomerce.entities.OrderItemEntity;
import com.dutra.dscomerce.entities.embedPk.OrderItemPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemPk> {
}
