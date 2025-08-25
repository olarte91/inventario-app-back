package com.konex.ventas_service.service;

import com.konex.ventas_service.dto.MedicineDto;
import com.konex.ventas_service.dto.MedicineRequestDto;
import com.konex.ventas_service.dto.MedicineResponseDto;
import com.konex.ventas_service.dto.SaleFilterDto;
import com.konex.ventas_service.dto.SaleResponseDto;
import com.konex.ventas_service.exception.InsuficientStockException;
import com.konex.ventas_service.mapper.SaleMapper;
import com.konex.ventas_service.model.Sale;
import com.konex.ventas_service.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class SaleService {

  private final RestTemplate restTemplate;
  private final SaleRepository saleRepository;
  private final SaleMapper saleMapper;

  @Value("${microservicio.medicamentos.url}")
  private String medicineServiceUrl;

  public SaleService(RestTemplate restTemplate,  SaleRepository saleRepository, SaleMapper mapper) {
    this.saleRepository = saleRepository;
    this.restTemplate = restTemplate;
    this.saleMapper = mapper;
  }

  //Bala trazadora
  public MedicineDto getMedicineById(UUID id){
    String url = medicineServiceUrl + id;
    return restTemplate.getForObject(url, MedicineDto.class);
  }

  public SaleResponseDto saleMedicine(UUID medicineId, MedicineRequestDto quantity){
    String url = medicineServiceUrl + medicineId;
    MedicineResponseDto medicine = restTemplate.getForObject(url, MedicineResponseDto.class);

    if(medicine.getStock() <= 0){
      throw new InsuficientStockException();
    }else{
      Sale sale = new Sale();
      sale.setSaleDate(LocalDateTime.now());
      sale.setMedicineId(medicineId);
      sale.setMedicineName(medicine.getName());
      sale.setQuantity(quantity.getQuantity());
      sale.setUnitPrice(medicine.getUnitPrice());
      sale.setTotal(quantity.getQuantity() *  medicine.getUnitPrice());

      saleRepository.save(sale);

      MedicineRequestDto medicineRequestDto = new MedicineRequestDto(quantity.getQuantity());
      restTemplate.put(url, medicineRequestDto);

      return saleMapper.toDto(sale);

    }
  }

  public List<SaleResponseDto> getSalesByFilter(SaleFilterDto filter){
    Specification<Sale> spec = SaleSpecification.build(filter);
    List<Sale> sales = saleRepository.findAll(spec);
    return sales.stream()
        .map(SaleMapper::toDto)
        .toList();
  }

}
