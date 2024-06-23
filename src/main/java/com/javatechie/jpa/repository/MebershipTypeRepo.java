package com.javatechie.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.jpa.entity.Membershiptype;

public interface MebershipTypeRepo extends JpaRepository<Membershiptype, Integer> {
	
	 Optional<Membershiptype> findByFirstnameAndLastname(String firstname, String lastname);

}
