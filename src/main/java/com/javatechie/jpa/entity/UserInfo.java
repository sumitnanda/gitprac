package com.javatechie.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "USERINFO_TBL")
@Data
public class UserInfo {

	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String lastname ;
	private String role;
	@OneToOne
	private Product product;

}
