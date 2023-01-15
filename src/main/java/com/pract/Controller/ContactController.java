package com.pract.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pract.model.Contact;
import com.pract.repository.ContactRepositoy;

@Controller
public class ContactController {

	@Autowired
	private ContactRepositoy contactRepo;
	
	@RequestMapping(value = "/saveMsg",method = RequestMethod.GET)
	public String contact(Model model) {
		model.addAttribute("contact", new Contact());
		return "contact.html";
	}
	
	@RequestMapping(value = "/saveMsg",method = RequestMethod.POST)
	public String contact(@ModelAttribute("contact") Contact contact,Model model) {
		contactRepo.save(contact);
		return "contact.html";
	}
}
