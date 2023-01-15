package com.pract.model;

import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "class")
public class EazyClass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator ="native")
	@GenericGenerator(strategy = "native",name="native")
	@Column(name = "class_id")
	private int classid;
	@Override
	public int hashCode() {
		return Objects.hash(classid, classname, login);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EazyClass other = (EazyClass) obj;
		return classid == other.classid && Objects.equals(classname, other.classname)
				&& Objects.equals(login, other.login);
	}

	@Column(name = "name")
	private String classname;
	
	@OneToMany(mappedBy = "eazyClass",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,targetEntity = Login.class)
	private Set<Login>login;

	public int getClassid() {
		return classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Set<Login> getLogin() {
		return login;
	}

	public void setLogin(Set<Login> login) {
		this.login = login;
	}
	
}
