package com.devsuperior.dslist.projection;


//INTERFACE PROJECTION SOMENTE COM METODOS GET
public interface GameMinProjection {

	Long getId();
	
	String getTitle();
	
	Integer getGameYear();
	
	String getImgUrl();
	
	String getShortDescription();
	
	Integer getPosition();
	
}
