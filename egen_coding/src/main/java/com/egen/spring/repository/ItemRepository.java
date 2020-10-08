package com.egen.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egen.spring.model.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>{

}
