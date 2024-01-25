package com.gustavo.tienda_de_chaquetas.model;

import org.springframework.data.mongodb.core.mapping.Document;

//import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.Setter;



//@Entity
//@Table(name = "roles")
@Document(collection = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;   //1:ADMIN 2:USER
    
    public Role(int id, String name){
    	this.id = id;
    	this.name = name;
    }
    
    public Role() {};
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;

}
