package com.javatechie.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	// private String category;
	private int quantity;
	private long price;

//	@OneToOne
//	private UserInfo user;

//    public Product(String name, int quantity, long price) {
//        this.name = name;
//        this.quantity = quantity;
//        this.price = price;
//    }

}
