package com.devsuperior.dslist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.ReplacementDTO;
import com.devsuperior.dslist.service.GameListService;
import com.devsuperior.dslist.service.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

	
	@Autowired
	private GameListService gameListService;

	@Autowired
	private GameService gameService;
	
	@GetMapping
	public List<GameListDTO> findAllList(){
		return gameListService.findAll();
	}
	
	@GetMapping(value="/{listId}/games")
	public List<GameDTO> findByList(@PathVariable Long listId)
	{
		return gameService.findByList(listId);
	}
	
	@PostMapping(value="/{listId}/replacement")
	public void move(@PathVariable Long listId,@RequestBody ReplacementDTO body)
	{
		gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
	}
	
}
