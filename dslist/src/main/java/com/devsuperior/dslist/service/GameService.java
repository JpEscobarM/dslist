package com.devsuperior.dslist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameFullDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projection.GameMinProjection;
import com.devsuperior.dslist.repository.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	
	@Transactional(readOnly=true)
	public List<GameDTO> findAll()
	{
		
		List<Game> listGame = gameRepository.findAll();
	
		List<GameDTO> dto = listGame.stream().map(x -> new GameDTO(x)).toList();
		
	
		return  dto;
	}
	
	
	
	@Transactional(readOnly=true)
	public GameFullDTO getById(Long id) {
		
		
		
		return new GameFullDTO(gameRepository.findById(id).get());
	}
	
	
	@Transactional(readOnly = true)
	public List<GameDTO> findByList(Long listId){
		return  gameRepository.searchByList(listId).stream().map(x -> new GameDTO(x)).toList();
	}
	
}

