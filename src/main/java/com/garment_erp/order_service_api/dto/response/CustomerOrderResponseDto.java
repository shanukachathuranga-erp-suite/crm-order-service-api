package com.garment_erp.order_service_api.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrderResponseDto {
    private String orderId;
    private Date orderDate;
    private String userId;
    private String remark;
    private String status;
    private double totalAmount;
    private List<OrderDetailResponseDto> orderDetails;
}
