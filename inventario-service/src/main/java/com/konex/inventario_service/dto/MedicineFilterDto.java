package com.konex.inventario_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MedicineFilterDto {
  private String name;
  private String laboratory;
  private LocalDate expirationBefore;
}
