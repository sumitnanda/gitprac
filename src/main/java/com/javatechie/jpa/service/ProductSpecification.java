package com.javatechie.jpa.service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.javatechie.jpa.entity.Product;
import com.javatechie.jpa.entity.UserInfo;

public class ProductSpecification {
	
	
	  public static Specification<Product> hasName(String name) {
	        return (root, query, criteriaBuilder) -> 
	            criteriaBuilder.equal(root.get("name"), name);
	    }
	  
	  public static Specification<Product> hasPCompanyName(String pname) {
	        return (root, query, criteriaBuilder) -> {
	            Join<Product, UserInfo> firstname = root.join("user", JoinType.INNER);
	            return criteriaBuilder.equal(firstname.get("firstname"), pname);
	        };
		  
	        //should not work
//		  return (root, query, criteriaBuilder) -> {
//	            Join<Product, UserInfo> firstname = root.join("product", JoinType.INNER);
//	            return criteriaBuilder.equal(firstname.get("firstname"), pname);
//	        };
	    }	
	  
	  
	  public static Specification<Product> belongsToBusinessGroup(String username) {
	        return (root, query, cb) -> {
	            if (username == null) {
	                return null;
	            }
	            System.out.println("inside ");
	            Subquery<Integer> subquery = query.subquery(Integer.class);
	            Root<UserInfo> userInfoRoot = subquery.from(UserInfo.class);
	            subquery.select(userInfoRoot.get("product").get("id"))
	                    .where(cb.equal(userInfoRoot.get("firstname"), username));
	            return root.get("id").in(subquery);
	        };
	    }
	  

//	    public static Specification<Product> hasCategory(String category) {
//	        return (root, query, criteriaBuilder) -> 
//	            criteriaBuilder.equal(root.get("category"), category);
//	    }

//	    public static Specification<Product> hasPriceGreaterThan(Double price) {
//	        return (root, query, criteriaBuilder) -> 
//	            criteriaBuilder.greaterThan(root.get("price"), price);
//	    }

}
