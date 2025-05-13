package com.garment_erp.order_service_api.service;

import com.garment_erp.order_service_api.dto.request.CustomerOrderRequestDto;
import com.garment_erp.order_service_api.dto.response.CustomerOrderResponseDto;
import com.garment_erp.order_service_api.dto.response.paginate.CustomerOrderPaginatedDto;

public interface CustomerOrderService {
    public void createOrder(CustomerOrderRequestDto requestDto);
    public void manageRemark(String remark, String orderId);
    public void manageStatus(String status, String orderId);
    public void updateOrder(CustomerOrderRequestDto requestDto, String orderId);
    public CustomerOrderResponseDto findOrderById(String orderId);
    public void deleteById(String orderId);
    public CustomerOrderPaginatedDto searchAll(String searchText, int page, int size);
}
