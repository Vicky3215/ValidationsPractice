package com.pract.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pract.model.EazyClass;

@Repository
public interface ClassRepository extends CrudRepository<EazyClass, Integer>{

}
