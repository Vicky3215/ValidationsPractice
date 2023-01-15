package com.pract.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contact {

	@Id
	@GeneratedValue(generator = "native",strategy = GenerationType.AUTO )
	@GenericGenerator(name = "native",strategy = "native")
	private int id;
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contact(int id, String msg, String rolll_no, String name) {
		super();
		this.id = id;
		this.msg = msg;
		this.rolll_no = rolll_no;
		this.name = name;
	}
	private String msg;
	@Column(name = "roll_no")
	private String rolll_no;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getRolll_no() {
		return rolll_no;
	}
	public void setRolll_no(String rolll_no) {
		this.rolll_no = rolll_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
