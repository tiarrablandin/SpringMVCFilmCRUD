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
		mv.setViewName("resultsSingle");
		return mv;
	}

	// Add code using keyword
	@RequestMapping(path = "GetFilmKeyword.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView searchByKeyword(String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> f = dao.findFilmByKeyWord(keyword);
		mv.addObject("film", f);
		mv.setViewName("resultsMulti");
		return mv;
	}

    // Add code for adding new film
    @RequestMapping(path = "AddFilm.do", method = RequestMethod.GET)
    public ModelAndView addFilm(Film film) {
    	film = dao.createFilm(film); 
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("resultsSingle");
    	return mv;
    }
    
    // Add code for delete film
    @RequestMapping(path = "deleteFilm.do", method = RequestMethod.GET)
    public ModelAndView deleteFilm(Film film) {
        ModelAndView mv = new ModelAndView();
        dao.deleteFilm(film);
        mv.setViewName("resultsSingle");
        return mv;
    }    
    
}

