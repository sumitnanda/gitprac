package com.javatechie.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "MEMBERSHIPTYPE_TBL",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"firstname", "lastname"})
})
@Data
public class Membershiptype {
	
	
	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String lastname ;
	private String role;

}
