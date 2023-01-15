package com.pract.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
@Entity
public class Courses {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator ="native")
	@GenericGenerator(name="native",strategy = "native")
	private int courseId;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	
	

	private String name;
	private String fees;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "courses")
	private List<Login>login=new ArrayList();
	public List<Login> getLogin() {
		return login;
	}
	public void setLogin(List<Login> login) {
		this.login = login;
	}
	@Override
	public int hashCode() {
		return Objects.hash(courseId, fees, login, name);
	}
	@Override
	public boolean equals(Object obj) {
		System.out.println("coming inside equaks"+obj);
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Courses other = (Courses) obj;
		System.out.println(login);
		System.out.println(other.login);
		System.out.println("hello");
		return courseId == other.courseId && Objects.equals(fees, other.fees) && Objects.equals(login, other.login)
				&& Objects.equals(name, other.name);
	}
}
