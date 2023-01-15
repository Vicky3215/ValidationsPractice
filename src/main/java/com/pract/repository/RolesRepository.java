package com.pract.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pract.model.Roles;

@Repository
public interface RolesRepository extends CrudRepository<Roles, Integer>{
   Roles getByRoleName(String roleName);
}
