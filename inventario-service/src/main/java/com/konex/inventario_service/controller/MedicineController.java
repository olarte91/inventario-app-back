package com.konex.inventario_service.controller;

import com.konex.inventario_service.dto.MedicineFilterDto;
import com.konex.inventario_service.dto.MedicineRequestDto;
import com.konex.inventario_service.dto.MedicineResponseDto;
import com.konex.inventario_service.dto.PagedResponse;
import com.konex.inventario_service.dto.StockUpdateRequestDto;
import com.konex.inventario_service.service.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController()
@RequestMapping("api/medicine")
@AllArgsConstructor
public class MedicineController {

  private final MedicineService medicineService;

  @GetMapping
  public ResponseEntity<PagedResponse<MedicineResponseDto>> getMedicines(
      @ModelAttribute MedicineFilterDto filter,
      Pageable pageable) {
    return ResponseEntity.ok(medicineService.getFilteredMedicines(filter, pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MedicineResponseDto> getMedicineById(@PathVariable UUID id) {
    return ResponseEntity.ok(medicineService.getMedicineById(id));
  }

  @PostMapping
  public ResponseEntity<MedicineResponseDto> createMedicine(@RequestBody MedicineRequestDto medicineRequestDto) {
    return ResponseEntity.ok(medicineService.saveMedicine(medicineRequestDto));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<MedicineResponseDto> updateMedicine(@PathVariable("id") UUID id, @RequestBody MedicineRequestDto medicineRequestDto) {
    return ResponseEntity.ok(medicineService.updateMedicine(id, medicineRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMedicine(@PathVariable("id") UUID id) {
    Boolean deleted = medicineService.deleteMedicine(id);
    return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> reduceStock(@PathVariable UUID id, @RequestBody StockUpdateRequestDto request) {
    medicineService.reduceStock(id, request.getQuantity());
    return ResponseEntity.ok().build();
  }
}
