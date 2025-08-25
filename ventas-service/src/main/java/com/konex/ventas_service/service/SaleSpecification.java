package com.konex.ventas_service.service;

import com.konex.ventas_service.dto.SaleFilterDto;
import com.konex.ventas_service.model.Sale;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleSpecification {

  public static Specification<Sale> build(SaleFilterDto filter){
    return (root, query, cb) ->{
      List<Predicate> predicates = new ArrayList<>();

      if(filter.getStartDate()!=null && filter.getEndDate()!=null){
        LocalDateTime start = filter.getStartDate().atStartOfDay();
        LocalDateTime end = filter.getEndDate().atTime(23, 59, 59);
        predicates.add(cb.between(root.get("saleDate"), start, end));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }
}
