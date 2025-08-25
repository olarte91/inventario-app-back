package com.konex.ventas_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class MedicineResponseDto {
  private UUID id;
  private String name;
  private String laboratory;
  private LocalDate expirationDate;
  private Integer stock;
  private Double unitPrice;
}
