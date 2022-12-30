package com.example.manageuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.manageuser.model.Country;

public interface CountryRepository extends JpaRepository<Country,Integer> {

}
