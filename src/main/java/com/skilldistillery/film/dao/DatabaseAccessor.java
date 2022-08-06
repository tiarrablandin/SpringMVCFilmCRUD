package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface DatabaseAccessor {
		
		  public Film findFilmById(int filmId);
		  public Actor findActorById(int actorId);
		  public List<Actor> findActorsByFilmId(int filmId);
		  public List<Film> findFilmByKeyWord(String key); 
		  public Film createFilm(Film Film); 
		//  public boolean saveFilm(Film film);
		  public boolean deleteFilm(Film film);


}

