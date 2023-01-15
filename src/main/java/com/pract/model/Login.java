package com.pract.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.internal.properties.Field;

import com.pract.annotation.FieldsValueMatch;
import com.pract.annotation.PasswordValidator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@FieldsValueMatch.List({
	@FieldsValueMatch(
		field="email",
		fieldMatch="confirmemail",
		message="Email should match"
	),
	@FieldsValueMatch(
		field="password",
				fieldMatch="confirmpassword",
				message="Password should match"
	)
	
})
@Table(name = "practice")
public class Login {
     
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(strategy = "native",name = "native")
	private int practice_id;
	public int getPractice_id() {
		return practice_id;
	}
	public void setPractice_id(int practice_id) {
		this.practice_id = practice_id;
	}
	@NotBlank(message = "This field cannot be null")
	private String email;
	@Transient
	private String confirmemail;
	@NotNull(message = "This field cannot be null")
	@PasswordValidator
	private String password;
	@Transient
	private String confirmpassword;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinTable(name = "person_courses",
    joinColumns = {
            @JoinColumn(name = "practice_id", referencedColumnName = "practice_id")},
    inverseJoinColumns = {
            @JoinColumn(name = "course_id", referencedColumnName = "courseId")})
	private List<Courses>courses=new ArrayList();
	
	public List<Courses> getCourses() {
		return courses;
	}
	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}
	public String getConfirmemail() {
		return confirmemail;
	}
	public void setConfirmemail(String confirmemail) {
		this.confirmemail = confirmemail;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	@NotNull(message = "This field cannot be null")
	private String classs;
	@NotNull(message = "This field cannot be null")
	@Size(min = 1,max = 3,message = "Size cnnot be greater than 3")
	private String age;
	@Size(min=6,max = 6,message = "Size should be 6 digits")
	private String roll_no;
	private String university;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClasss() {
		return classs;
	}
	public void setClasss(String classs) {
		this.classs = classs;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getRoll_no() {
		return roll_no;
	}
	public void setRoll_no(String roll_no) {
		this.roll_no = roll_no;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,targetEntity = Roles.class)
	@JoinColumn(name="role_id",nullable = false,referencedColumnName = "roleId")
	private Roles roles;
	
	public EazyClass getEazyClass() {
		return eazyClass;
	}
	public void setEazyClass(EazyClass eazyClass) {
		this.eazyClass = eazyClass;
	}
	@ManyToOne(fetch = FetchType.EAGER,optional = true)
	@JoinColumn(name="class_id",referencedColumnName = "class_id",nullable = true)
	private EazyClass eazyClass;
	
	
	
	public Roles getRoles() {
		return roles;
	}
	@Override
	public int hashCode() {
		return Objects.hash(age, classs, confirmemail, confirmpassword, courses, eazyClass, email, password,
				practice_id, roles, roll_no, university);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(age, other.age) && Objects.equals(classs, other.classs)
				&& Objects.equals(confirmemail, other.confirmemail)
				&& Objects.equals(confirmpassword, other.confirmpassword) && Objects.equals(courses, other.courses)
				&& Objects.equals(eazyClass, other.eazyClass) && Objects.equals(email, other.email)
				&& Objects.equals(password, other.password) && practice_id == other.practice_id
				&& Objects.equals(roles, other.roles) && Objects.equals(roll_no, other.roll_no)
				&& Objects.equals(university, other.university);
	}
	@Override
	public String toString() {
		return "Login [practice_id=" + practice_id + ", email=" + email + ", confirmemail=" + confirmemail
				+ ", password=" + password + ", confirmpassword=" + confirmpassword + ", courses=" + courses
				+ ", classs=" + classs + ", age=" + age + ", roll_no=" + roll_no + ", university=" + university
				+ ", roles=" + roles + ", eazyClass=" + eazyClass + "]";
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}
