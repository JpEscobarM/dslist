package com.devsuperior.dslist.dto;

import com.devsuperior.dslist.entities.GameList;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class GameListDTO {
	
	private Long id;
	
	private String name;
	
	
	public GameListDTO(GameList gameList) {
		this.id = gameList.getId();
		this.name = gameList.getName();
	}


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}
	
	
	
}
