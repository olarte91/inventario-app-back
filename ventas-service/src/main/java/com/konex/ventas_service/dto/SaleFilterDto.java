package com.konex.ventas_service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class SaleFilterDto {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDate startDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDate endDate;
}
