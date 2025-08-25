package com.konex.inventario_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineRequestDto {
  private String name;
  private String laboratory;
  private LocalDate manufacturingDate;
  private LocalDate expirationDate;
  private Integer stock;
  private Double unitPrice;
}
