package com.garment_erp.order_service_api.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailResponseDto {
    private String detailId;
    private String productId;
    private int qty;
    private double unitPrice;
    private double discount;
}
