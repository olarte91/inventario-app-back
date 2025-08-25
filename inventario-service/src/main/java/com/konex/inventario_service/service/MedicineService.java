package com.konex.inventario_service.service;

import com.konex.inventario_service.dto.MedicineFilterDto;
import com.konex.inventario_service.dto.MedicineRequestDto;
import com.konex.inventario_service.dto.MedicineResponseDto;
import com.konex.inventario_service.dto.PagedResponse;
import com.konex.inventario_service.exception.InsufficientStockException;
import com.konex.inventario_service.exception.MedicineNotFoundException;
import com.konex.inventario_service.mapper.MedicineMapper;
import com.konex.inventario_service.model.Medicine;
import com.konex.inventario_service.repository.MedicineRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class MedicineService {

  private final MedicineRepository medicineRepository;
  private final MedicineMapper medicineMapper;

  public PagedResponse<MedicineResponseDto> getFilteredMedicines(MedicineFilterDto filter, Pageable pageable) {
    Specification<Medicine> spec = MedicineSpecification.build(filter);
    Page<Medicine> page = medicineRepository.findAll(spec, pageable);

    List<MedicineResponseDto> content = page.getContent()
        .stream()
        .map(medicineMapper::toDto)
        .toList();

    return new PagedResponse<>(
        content,
        page.getNumber(),
        page.getSize(),
        page.getTotalElements(),
        page.getTotalPages(),
        page.isLast()
    );
  }

  public MedicineResponseDto saveMedicine(MedicineRequestDto medicineRequestDto) {
    Medicine entity = medicineMapper.toEntity(medicineRequestDto);

    medicineRepository.save(entity);

    return medicineMapper.toDto(entity);
  }

  public MedicineResponseDto updateMedicine(UUID id, MedicineRequestDto medicineRequestDto) {
    Medicine medicine = medicineRepository.findById(id)
        .orElseThrow(MedicineNotFoundException::new);
    medicineMapper.updateEntityFromDto(medicineRequestDto, medicine);
    medicineRepository.save(medicine);
    return medicineMapper.toDto(medicine);
  }

  public Boolean deleteMedicine(UUID id) {
    if (medicineRepository.existsById(id)) {
      medicineRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  public MedicineResponseDto getMedicineById(UUID id) {
    Medicine medicine = medicineRepository.findById(id)
        .orElseThrow(MedicineNotFoundException::new);
    return medicineMapper.toDto(medicine);
  }

  public void reduceStock(UUID id, Integer quantity) {
    Medicine medicine = medicineRepository.findById(id)
        .orElseThrow(MedicineNotFoundException::new);

    if(medicine.getStock() < quantity){
      throw new InsufficientStockException();
    }

    medicine.setStock(medicine.getStock() - quantity);
    medicineRepository.save(medicine);
  }
}
