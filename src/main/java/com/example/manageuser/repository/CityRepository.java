package com.example.manageuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.manageuser.model.City;

public interface CityRepository extends JpaRepository<City, Integer> {

	@Query(value="select * from city where state_id=?",nativeQuery = true)
	List<City> findCitiesByStateId(Integer stateId);

}
