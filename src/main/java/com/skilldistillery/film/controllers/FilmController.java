package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.DatabaseAccessor;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	private DatabaseAccessor dao;

	// Add code using filmID
	@RequestMapping(path = "GetFilmId.do", params = "id", method = RequestMethod.GET)
	public ModelAndView searchByFilmId(int id) {
		ModelAndView mv = new ModelAndView();
		Film f = dao.findFilmById(id);
		 mv.addObject("film", f);
		mv.setViewName("results");
		return mv;
	}

	// Add code using keyword
	@RequestMapping(path = "GetFilmKeyword.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView searchByKeyword(String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> f = dao.findFilmByKeyWord(keyword);
		mv.addObject("film", f);
		mv.setViewName("results");
		return mv;
	}

	// Add code for adding new film
	@RequestMapping(path = "AddFilm.do", params = "addFilm", method = RequestMethod.GET)
	public ModelAndView addNewFilm(String title, int languae){
		Film film = new Film(title,languae);
		ModelAndView mv = new ModelAndView();
		Film f = dao.createFilm(film);
		mv.addObject("film", f);
		mv.setViewName("results");
		return mv;
	}
	
	// Add code for adding new film
	@RequestMapping(path = "deleteFilm.do", params = "deleteFilm", method = RequestMethod.GET)
	public ModelAndView deleteNewFilm(Film film) {

	}	
	
}
