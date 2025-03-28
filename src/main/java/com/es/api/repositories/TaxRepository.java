package com.es.api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.es.api.entities.TaxEntity;


@Repository
public interface TaxRepository extends JpaRepository<TaxEntity, Long> {
 
    Page<TaxEntity> findAllByUserId(Long userId, Pageable pageable);

    Optional<TaxEntity> findByUserIdAndId(Long userId, Long taxId);

}
