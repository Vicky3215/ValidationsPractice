package com.pract.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pract.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer>{
      Login getByEmail(String name);
      
     List<Login>findByEmail(String name);
}
