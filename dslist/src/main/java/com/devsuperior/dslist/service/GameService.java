package com.devsuperior.dslist.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repository.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	public List<GameDTO> findAll()
	{
		
		List<Game> listGame = gameRepository.findAll();
	
		List<GameDTO> dto = listGame.stream().map(x -> new GameDTO(x)).toList();
		
		
		//FORMA SIMPLES
		/*List<GameDTO> listGameDTO = new LinkedList<GameDTO>();
		
		for(Game g : listGame)
		{
			
			listGameDTO.add(new GameDTO(g));
			
		}*/
		
		return  dto;
	}
	
	
}
