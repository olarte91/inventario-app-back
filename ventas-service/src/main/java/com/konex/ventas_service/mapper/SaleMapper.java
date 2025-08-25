package com.konex.ventas_service.mapper;

import com.konex.ventas_service.dto.SaleResponseDto;
import com.konex.ventas_service.model.Sale;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SaleMapper {

  public static SaleResponseDto toDto(Sale sale){
    return new SaleResponseDto(
        sale.getId(),
        sale.getMedicineId(),
        sale.getMedicineName(),
        sale.getSaleDate(),
        sale.getQuantity(),
        sale.getUnitPrice(),
        sale.getTotal()
    );
  }
}
