package com.devsuperior.dslist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dslist.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Long>{

}
