package com.garment_erp.order_service_api.service.impl;

import com.garment_erp.order_service_api.dto.request.CustomerOrderRequestDto;
import com.garment_erp.order_service_api.dto.request.OrderDetailRequestDto;
import com.garment_erp.order_service_api.dto.response.CustomerOrderResponseDto;
import com.garment_erp.order_service_api.dto.response.OrderDetailResponseDto;
import com.garment_erp.order_service_api.entity.CustomerOrder;
import com.garment_erp.order_service_api.entity.OrderDetail;
import com.garment_erp.order_service_api.entity.OrderStatus;
import com.garment_erp.order_service_api.repo.CustomerOrderRepo;
import com.garment_erp.order_service_api.repo.OrderStatusRepo;
import com.garment_erp.order_service_api.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepo customerOrderRepo;
    private final OrderStatusRepo orderStatusRepo;

    @Override
    public void createOrder(CustomerOrderRequestDto requestDto) {

        OrderStatus orderStatus =  orderStatusRepo.findByStatus("PENDING").orElseThrow(
                ()-> new RuntimeException("Order status not found")
        );

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderId(UUID.randomUUID().toString());
        customerOrder.setOrderDate(requestDto.getOrderDate());
        customerOrder.setRemark("");
        customerOrder.setTotalAmount(requestDto.getTotalAmount());
        customerOrder.setUserId(requestDto.getUserId());

        customerOrder.setOrderStatus(orderStatus);
        Set<OrderDetail> orderSet = requestDto.getOrderDetails().stream().map(
                e->createOrderDetail(e, customerOrder)).collect(Collectors.toSet());
        customerOrder.setProducts(orderSet);

        customerOrderRepo.save(customerOrder);

    }

    private OrderDetail createOrderDetail(OrderDetailRequestDto requestDto, CustomerOrder order){
        if (requestDto == null) {
            return null;
        }

        return OrderDetail.builder()
                .detailId(UUID.randomUUID().toString())
                .unitPrice(requestDto.getUnitPrice())
                .discount(requestDto.getDiscount())
                .qty(requestDto.getQty())
                .customerOrder(order)
                .build();

    }

    public CustomerOrderResponseDto findOrderById(String orderId){

        CustomerOrder customerOrder = customerOrderRepo.findById(orderId).orElseThrow(
                ()->new RuntimeException(String.format("Order nor found with %s", orderId))
        );

        return toCustomerOrderResponseDto(customerOrder);

    }

    private CustomerOrderResponseDto toCustomerOrderResponseDto(CustomerOrder customerOrder){

        if(customerOrder == null){
            return null;
        }
        return CustomerOrderResponseDto.builder()
                .orderId(customerOrder.getOrderId())
                .orderDate(customerOrder.getOrderDate())
                .userId(customerOrder.getUserId())
                .totalAmount(customerOrder.getTotalAmount())
                .orderDetails(customerOrder.getProducts().stream().map(
                        this::toOrderDetailResponseDto
                ).collect(Collectors.toList())
                )
                .remark(customerOrder.getRemark())
                .status(customerOrder.getOrderStatus().getStatus())
                .build();



    }

    private OrderDetailResponseDto toOrderDetailResponseDto(OrderDetail orderDetail){
        if (orderDetail == null) {
            return null;
        }

        return OrderDetailResponseDto.builder()
                .productId(orderDetail.getProductId())
                .detailId(orderDetail.getDetailId())
                .qty(orderDetail.getQty())
                .unitPrice(orderDetail.getUnitPrice())
                .discount(orderDetail.getDiscount())
                .build();
    }

}


