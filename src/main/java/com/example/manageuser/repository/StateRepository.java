package com.example.manageuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.manageuser.model.State;

public interface StateRepository  extends JpaRepository<State,Integer>{

	@Query(value="select * from state where countryId=?",nativeQuery = true)
	List<State> findStatesByCountryId(Integer countryId);
	
}
