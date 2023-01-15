package com.pract.rest;

import java.awt.PageAttributes.MediaType;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pract.model.Contact;
import com.pract.model.Login;
import com.pract.model.Response;
import com.pract.repository.ContactRepositoy;
import com.pract.repository.LoginRepository;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(path="/api",produces = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE,org.springframework.http.MediaType.APPLICATION_XML_VALUE})
@CrossOrigin(origins="*")
public class RestController {

	
	@Autowired
	private ContactRepositoy contactRepo;
	
	
	@RequestMapping(value = "/getMessage",method = RequestMethod.GET)
	//@ResponseBody
	public List<Contact>getMessage(@RequestParam("name")String name){
		System.out.println("coming inside rest contoller");
		return  (List<Contact>) contactRepo.getByName(name);
	}
	
	@RequestMapping(value = "/getAllMessageByStatus",method = RequestMethod.GET)
	//@ResponseBody
	public List<Contact>getAllMessageByStatus(@RequestBody Contact contact){
		System.out.println("contact"+contact.getName());
		List<Contact>cn= contactRepo.getByName(contact.getName());
		System.out.println("cn"+cn);
		return  cn;
	}
	
	@RequestMapping(value = "/getByStatus",method = RequestMethod.GET)
	//@ResponseBody
	public Contact getByStatus(@RequestBody Contact contact){
		System.out.println("contact"+contact.getName());
		System.out.println("coming inside getByStatus");
		return  contactRepo.findByName(contact.getName());
	}
	
	@RequestMapping("/deleteMsg")
	public ResponseEntity<Response>saveMsg(RequestEntity<Contact>contact){
		org.springframework.http.HttpHeaders headers=contact.getHeaders();
		System.out.println("headers"+headers);
		Contact contact1=contact.getBody();
		System.out.println("contact1"+contact1.getName());
		contactRepo.deleteById(contact1.getId());
		Response res=new Response();
		res.setStatusCode("201");
		res.setStatusMsg("Message deleted successfully");
		return ResponseEntity.status(HttpStatus.CREATED).header("isSaved", "true").body(res);
	}
	
	@PatchMapping("/updateName")
	public ResponseEntity<Response>updateName(@RequestBody Contact con){
		Response res=new Response();
		Optional<Contact> contact=contactRepo.findById(con.getId());
		if(contact.isPresent()) {
			contact.get().setName("annnnn");
			
			contactRepo.save(contact.get());
			res.setStatusCode("201");
			res.setStatusMsg("Message deleted successfully");
		}else {
			res.setStatusCode("401");
			res.setStatusMsg("Inavlid Id");
		}
		
		
		return ResponseEntity.status(HttpStatus.CREATED).header("isSaved", "true").body(res);
	}
	@PostMapping("/saveMsg")
	public ResponseEntity<Response>saveMsg(@RequestHeader("invocationfrom") String info,@RequestBody Contact contact){
		
		contactRepo.save(contact);
		Response res=new Response();
		res.setStatusCode("200");
		res.setStatusMsg("Msg is successfully saved");
		return ResponseEntity.status(HttpStatus.CREATED).header("isSaved", "true").body(res);
	}
}
