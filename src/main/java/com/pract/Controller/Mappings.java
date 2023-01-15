package com.pract.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pract.model.Courses;
import com.pract.model.EazyClass;
import com.pract.model.Login;
import com.pract.repository.ClassRepository;
import com.pract.repository.LoginRepository;
import com.pract.repository.coursesRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class Mappings {
     
	@Autowired
	private ClassRepository classRepo;
	
	@Autowired
	private LoginRepository loginRepo;
	@Autowired
	private coursesRepository courseRepo;
	
	
	@RequestMapping(value = "/displayClasses",method = RequestMethod.GET)
	public ModelAndView displayClasses() {
		List<EazyClass>clss=(List<EazyClass>) classRepo.findAll();
		System.out.println("found class"+clss.getClass());
		ModelAndView model=new ModelAndView();
		model.addObject("classes", clss);
		model.setViewName("displayClasses.html");
		
      return model;		
	}
	
	@RequestMapping(value = "/addClasss",method = RequestMethod.GET)
	public String addNewClasss(Model model) {
		
		model.addAttribute("eazyclass", new EazyClass());
		return "class.html";
	}
	
	@RequestMapping(value = "/addClass",method = RequestMethod.POST)
	public String addNewClass(@ModelAttribute("eazyclass")EazyClass classs,Model model) {
		System.out.println("classss"+classs.getClassname());
		classRepo.save(classs);
		return "redirect:/displayClasses";
	}
	
	@RequestMapping(value = "/displayStudents",method = RequestMethod.GET)
	public String displayStudents(@RequestParam("classid")Integer id,Model model,HttpSession session) {
		System.out.println("Mappings.displayStudents()"+id);
		Optional<EazyClass> classs=classRepo.findById(id);
		model.addAttribute("eazyclass", classs.get());
		session.setAttribute("classe", classs.get());
		return "displayStudents.html";
		
	}
	
	@RequestMapping(value = "/addStudent",method = RequestMethod.GET)
	public ModelAndView addStudentPage(Model model) {
		ModelAndView mode1l=new ModelAndView();
		mode1l.addObject("student", new Login());
		mode1l.setViewName("students");
		return mode1l;
	}
	
	@RequestMapping(value = "/addStudent",method = RequestMethod.POST)
	public ModelAndView addStudent(@ModelAttribute("student")Login student,HttpSession session) {
		ModelAndView model=new ModelAndView();
		Login lgin=loginRepo.getByEmail(student.getEmail());
		EazyClass classt=(EazyClass) session.getAttribute("classe");
		System.out.println("saving student");
		lgin.setEazyClass(classt);
		loginRepo.save(lgin);
		classt.getLogin().add(lgin);
		classRepo.save(classt);
		model.setViewName("redirect:/displayStudents?classid="+classt.getClassid());
		return model;
		
	}
	
	@RequestMapping(value = "/deleteClass",method = RequestMethod.GET)
	public ModelAndView deleteClass(@RequestParam("classid")Integer id,HttpSession session) {
		ModelAndView model=new ModelAndView();
		Optional<EazyClass> classs=classRepo.findById(id);
		for(Login login:classs.get().getLogin()) {
			login.setEazyClass(null);
			loginRepo.save(login);
		}
		classRepo.deleteById(id);
		
		model.setViewName("redirect:/displayClasses");
		return model;
		
	}
	
	
	
	
	@RequestMapping(value = "/deleteStudent",method = RequestMethod.GET)
	public ModelAndView deleteStudent(@RequestParam("id") Integer id,HttpSession session) {
		ModelAndView model=new ModelAndView();
		Optional<Login> login =loginRepo.findById(id);
		login.get().setEazyClass(null);
		
		EazyClass classt=(EazyClass) session.getAttribute("classe");
		System.out.println("saving student");
		classt.getLogin().remove(login);
		
		
		classRepo.save(classt);
		model.setViewName("redirect:/displayStudents?classid="+classt.getClassid());
		return model;
		
	}
	
	
	@RequestMapping(value = "/disCourses",method = RequestMethod.GET)
	public ModelAndView dispCourses() {
		ModelAndView model=new ModelAndView();
		List<Courses>courses= courseRepo.findAll();
		model.addObject("courses", courses);
		model.setViewName("discourses");
		return model;
	}
	
	@RequestMapping(value = "/addnewCourse",method = RequestMethod.GET)
	public ModelAndView addCourses() {
		ModelAndView model=new ModelAndView();
		model.addObject("courses", new Courses());
		model.setViewName("addcourses");
		return model;
	}
	
	@RequestMapping(value = "/addnewCourse",method = RequestMethod.POST)
	public String addCoursess(@ModelAttribute("courses")Courses couses) {
		ModelAndView model=new ModelAndView();
		courseRepo.save(couses);
		System.out.println("course is saved");
		return "redirect:/disCourses";
	}
	
	@RequestMapping(value = "/viewStudents",method = RequestMethod.GET)
	public ModelAndView addCoursess(@RequestParam("courseid")Integer id,HttpSession session){
		ModelAndView model=new ModelAndView();
		
		Optional<Courses> courses=courseRepo.findById(id);
		System.out.println("courses.get"+courses.get());
		System.out.println("courses.get"+courses.get().getLogin());
		model.addObject("courses", courses.get());
		model.setViewName("viewstudents");
		session.setAttribute("courses1", courses.get());
		return model;
	}
	

	@RequestMapping(value = "/addNewStudentInthiscouse",method = RequestMethod.GET)
	public ModelAndView addNewStudentInthiscouse(){
		
		ModelAndView model=new ModelAndView();
		model.addObject("login", new Login());
		model.setViewName("addNewStudentInthiscouse");
		return model;
	}
	
	@RequestMapping(value = "/addNewStudentInthiscouse",method = RequestMethod.POST)
	public ModelAndView addNewStudentInthiscouse1(@ModelAttribute("login")Login login,HttpSession session){
		ModelAndView model=new ModelAndView();
		Courses courses=(Courses) session.getAttribute("courses1");
		Login login1=loginRepo.getByEmail(login.getEmail());
		login1.getCourses().add(courses);
		loginRepo.save(login1);
		courses.getLogin().add(login1);
		courseRepo.save(courses);
		session.setAttribute("courses1", courses);
		model.setViewName("redirect:/viewStudents?courseid="+courses.getCourseId());
		return model;
	}
	
	@RequestMapping(value = "/deleteStudentfomthisCourse",method = RequestMethod.GET)
	public ModelAndView deleteStudentfomthisCourse(@RequestParam("id")Integer id,HttpSession session){
		ModelAndView model=new ModelAndView();
		Courses courses=(Courses) session.getAttribute("courses1");
		Optional<Courses> corr=courseRepo.findById(courses.getCourseId());
        
		Optional<Login> login1=loginRepo.findById(id);
		System.out.println("correquals"+corr.get().equals(login1.get().getCourses().get(0)));
		System.out.println("login1get"+login1.get().getCourses());
		login1.get().getCourses().remove(corr.get());
		System.out.println("login1get"+login1.get().getCourses());
		
		loginRepo.save(login1.get());
		courses.getLogin().remove(login1);
		//courseRepo.save(courses);
		session.setAttribute("courses1", courses);
		
		model.setViewName("redirect:/viewStudents?courseid="+courses.getCourseId());
		return model;
	}
	
	
	
	
}
