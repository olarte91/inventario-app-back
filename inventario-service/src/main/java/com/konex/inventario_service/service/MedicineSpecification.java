package com.konex.inventario_service.service;

import com.konex.inventario_service.dto.MedicineFilterDto;
import com.konex.inventario_service.model.Medicine;
import jakarta.persistence.criteria.Predicate;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MedicineSpecification {
  public static Specification<Medicine> build(MedicineFilterDto filter){
    return (root, query, cb) ->{
      List<Predicate> predicates = new ArrayList<>();

      if(filter.getName() != null){
        predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
      }
      if(filter.getLaboratory() != null){
        predicates.add(cb.like(root.get("laboratory"), "%" + filter.getLaboratory() + "%"));
      }
      if(filter.getExpirationBefore() != null){
        predicates.add(cb.lessThan(root.get("expirationDate"),filter.getExpirationBefore()));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }
}
