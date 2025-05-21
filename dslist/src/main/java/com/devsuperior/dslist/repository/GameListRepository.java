package com.devsuperior.dslist.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dslist.entities.GameList;

@Repository
public interface GameListRepository extends JpaRepository<GameList,Long>{

	
	
}
