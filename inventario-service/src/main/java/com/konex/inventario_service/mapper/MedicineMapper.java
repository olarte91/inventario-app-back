package com.konex.inventario_service.mapper;

import com.konex.inventario_service.dto.MedicineRequestDto;
import com.konex.inventario_service.dto.MedicineResponseDto;
import com.konex.inventario_service.model.Medicine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MedicineMapper {

  public MedicineResponseDto toDto(Medicine medicine){
    MedicineResponseDto medicineResponseDto = new MedicineResponseDto();
    medicineResponseDto.setId(medicine.getId());
    medicineResponseDto.setName(medicine.getName());
    medicineResponseDto.setLaboratory(medicine.getLaboratory());
    medicineResponseDto.setStock(medicine.getStock());
    medicineResponseDto.setUnitPrice(medicine.getUnitPrice());
    medicineResponseDto.setExpirationDate(medicine.getExpirationDate());

    return medicineResponseDto;
  }

  public Medicine toEntity(MedicineRequestDto medicineRequestDto){
    Medicine medicine = new Medicine();
    medicine.setName(medicineRequestDto.getName());
    medicine.setLaboratory(medicineRequestDto.getLaboratory());
    medicine.setStock(medicineRequestDto.getStock());
    medicine.setUnitPrice(medicineRequestDto.getUnitPrice());
    medicine.setExpirationDate(medicineRequestDto.getExpirationDate());
    medicine.setManufacturingDate(medicineRequestDto.getManufacturingDate());
    return medicine;
  }

  public void updateEntityFromDto(MedicineRequestDto dto, Medicine medicine){
    if(dto.getName() != null){
      medicine.setName(dto.getName());
    }
    if(dto.getLaboratory() != null){
      medicine.setLaboratory(dto.getLaboratory());
    }
    if(dto.getManufacturingDate() != null){
      medicine.setManufacturingDate(dto.getManufacturingDate());
    }
    if(dto.getExpirationDate() != null){
      medicine.setExpirationDate(dto.getExpirationDate());
    }
    if(dto.getStock() != null){
      medicine.setStock(dto.getStock());
    }
    if(dto.getUnitPrice() != null){
      medicine.setUnitPrice(dto.getUnitPrice());
    }
  }

}
