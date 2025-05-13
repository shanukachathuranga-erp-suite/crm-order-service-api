package com.garment_erp.order_service_api.api;

import com.garment_erp.order_service_api.dto.request.CustomerOrderRequestDto;
import com.garment_erp.order_service_api.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer-orders")
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    @PostMapping("/business")
    public String create(CustomerOrderRequestDto request){
        customerOrderService.createOrder(request);
    }

    @GetMapping("/visitors/find-by-id/{id}")
    public String create(@PathVariable String id){
        customerOrderService.findOrderById(id);
    }


}
