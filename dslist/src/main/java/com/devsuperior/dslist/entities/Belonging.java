package com.devsuperior.dslist.entities;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_belonging")
public class Belonging {
	
	
	@EmbeddedId
	private BelongingPK id = new BelongingPK();
	
	private Integer position;
	
	Belonging()
	{
		
	}

	public Belonging(Game g, GameList gl, Integer position) {
		super();
		this.id.setGame(g);
		this.id.setGameList(gl);
		this.position = position;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Belonging other = (Belonging) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
