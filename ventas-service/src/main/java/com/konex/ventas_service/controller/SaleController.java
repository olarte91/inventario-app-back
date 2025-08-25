package com.konex.ventas_service.controller;

import com.konex.ventas_service.dto.MedicineDto;
import com.konex.ventas_service.dto.MedicineRequestDto;
import com.konex.ventas_service.dto.SaleFilterDto;
import com.konex.ventas_service.dto.SaleRequestDto;
import com.konex.ventas_service.dto.SaleResponseDto;
import com.konex.ventas_service.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class SaleController {

  private final SaleService saleService;

  //Bala trazadora
  @GetMapping("/medicine/{id}")
  public ResponseEntity<MedicineDto> getMedicine(@PathVariable UUID id) {
    MedicineDto dto = saleService.getMedicineById(id);
    return ResponseEntity.ok(dto);
  }

  @PostMapping("/{id}")
  public ResponseEntity<SaleResponseDto> saleMedicine(@PathVariable UUID id, @RequestBody MedicineRequestDto quantity) {
    return ResponseEntity.ok(saleService.saleMedicine(id, quantity));
  }

   @GetMapping
  public ResponseEntity<List<SaleResponseDto>> getSales(@ModelAttribute SaleFilterDto filter){
      return ResponseEntity.ok(saleService.getSalesByFilter(filter));
   }

}
