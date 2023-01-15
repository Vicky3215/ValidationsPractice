package com.pract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pract.model.Courses;
@Repository
public interface coursesRepository extends JpaRepository<Courses,Integer>{

}
