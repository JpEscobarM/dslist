package com.devsuperior.dslist.dto;

import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projection.GameMinProjection;

public class GameDTO {
	
	private Long id;
	
	private String title;
	
	private Integer year;
	
	private String imgUrl;

	private String shortDescription;

	public GameDTO()
	{
		
	}
	
	public GameDTO(Long id, String title, Integer year, String imgUrl, String shortDescription) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.imgUrl = imgUrl;
		this.shortDescription = shortDescription;
	}
	
	public GameDTO(GameMinProjection entity) {
		super();
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.year = entity.getGameYear();
		this.imgUrl = entity.getImgUrl();
		this.shortDescription = entity.getShortDescription();
	}
	

	public GameDTO(Game entity)
	{
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.year = entity.getYear();
		this.imgUrl = entity.getImgUrl();
		this.shortDescription = entity.getShortDescription();
		
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getYear() {
		return year;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public String getShortDescription() {
		return shortDescription;
	}
	
	
}
