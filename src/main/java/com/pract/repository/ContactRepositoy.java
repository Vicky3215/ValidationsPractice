package com.pract.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pract.model.Contact;

public interface ContactRepositoy extends CrudRepository<Contact, Integer>{

	List<Contact>getByName(String name);
	
	Contact findByName(String name);
}
