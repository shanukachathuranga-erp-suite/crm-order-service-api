package com.garment_erp.order_service_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="order_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @Column(name = "detail_id", unique = true, nullable = false, length = 80)
    private String detailId;
    @Column(name = "product_id", nullable = false)
    private String productId;
    @Column(name = "qty", nullable = false)
    private int qty;
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private double unitPrice;
    @Column(name = "discount", precision = 10, scale = 2)
    private double discount;
    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;

}
