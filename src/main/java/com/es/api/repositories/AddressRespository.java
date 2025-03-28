package com.es.api.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.es.api.entities.AddressEntity;


@Repository
public interface AddressRespository extends JpaRepository<AddressEntity, Long> {
    
    
    Page<AddressEntity> findAllByUserId(Long userId, Pageable pageable);

    Optional<AddressEntity> findByUserIdAndId(Long userId, Long addressId);

    

}
