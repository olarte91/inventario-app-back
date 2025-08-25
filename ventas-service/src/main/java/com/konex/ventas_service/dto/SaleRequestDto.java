package com.konex.ventas_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class SaleRequestDto {
  private UUID medicineId;
  private Integer quantity;
}
