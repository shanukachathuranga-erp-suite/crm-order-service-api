package com.garment_erp.order_service_api.dto.response.paginate;

import com.garment_erp.order_service_api.dto.response.CustomerOrderResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrderPaginatedDto {
    private long count;
    private List<CustomerOrderResponseDto> dataList;
}
