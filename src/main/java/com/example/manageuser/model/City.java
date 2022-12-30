	package com.example.manageuser.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class City {

	@Id
	@GeneratedValue
	private Integer id;
	private String cityName;
	private Integer stateId;
}
