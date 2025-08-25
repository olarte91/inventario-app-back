package com.konex.ventas_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseDto {
  private UUID id;
  private UUID medicineId;
  private String medicineName;
  private LocalDateTime saleDate;
  private Integer quantity;
  private Double unitPrice;
  private Double total;

}
