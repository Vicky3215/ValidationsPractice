package com.pract.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pract.sevice.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class FirstController {

	@Autowired
	private LoginService sevice;
	
	@RequestMapping(value={"/","/home"})
	public String home(Model model) {
		System.out.println("coming in");
		return "home.html";
	}
	
	@RequestMapping(value={"/login"})
	public String Login(Model model) {
		model.addAttribute("login", new com.pract.model.Login());
		System.out.println("coming inside login");
		return "login.html";
	}
	
	@RequestMapping(value={"/loginO"} ,method = {RequestMethod.GET,RequestMethod.POST})
	public String LoginO(Model model,@RequestParam(value = "error",required = false)String error,@RequestParam(value = "logout",required = false)String logout) {
		String errorMsg="";
		if(error!=null) {
			errorMsg="Username or password is incorrect!";
		}
		
		if(logout!=null) {
			errorMsg="You have been successfully LoggedOut!";
		}
		model.addAttribute("errorMsg", errorMsg);
		System.out.println("coming in");
		return "loginO.html";
	}
	
	@RequestMapping(value={"/dashboad"} ,method = {RequestMethod.GET,RequestMethod.POST})
	public String dashboad(Model model,Authentication auth) {
		String usename=auth.getName();
		String roles=auth.getAuthorities().toString();
		System.out.println("useanm"+usename);
		System.out.println("roles"+roles);
		System.out.println("coming in dashboad");
		model.addAttribute("usename", usename);
		model.addAttribute("roles", roles);
		return "dashboad.html";
	}
	
	@RequestMapping(value={"/addlogin"},method = RequestMethod.POST)
	public String Loginn(@Valid @ModelAttribute("login")com.pract.model.Login login,Errors errors) {
		System.out.println("coming in add/login");
		if(errors.hasErrors()) {
			System.out.println("It contain errors");
			return "login.html";
		}else {
			System.out.println("This is the login inside just befoe saving the data");
			sevice.isSave(login);
		}
		return "login.html";
	}
	
	@RequestMapping(value="/about",method = RequestMethod.GET)
	public String about() {
		return "about.html";
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		System.out.println("ath"+auth);
		if(auth!=null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/loginO?logout=true";
	}
	
}
