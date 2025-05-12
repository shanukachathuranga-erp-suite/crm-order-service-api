package com.garment_erp.order_service_api.repo;

import com.garment_erp.order_service_api.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepo extends JpaRepository<OrderDetail, String> {

}
