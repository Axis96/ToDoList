package it.dstech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import it.dstech.model.Attività;
import it.dstech.model.Utente;
import it.dstech.service.AttivitàService;
import it.dstech.service.UtenteService;


public class UtenteController {

	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private AttivitàService attivitàService;
	
	@PostMapping(value = { "/utente/creaAttività" })
	public ModelAndView creaEsame(Attività attività, Long id) {
		ModelAndView modelAndView = new ModelAndView();
		Utente utente = utenteService.get(id);
		Attività nuovaAttività = new Attività();
		

		nuovaAttività.setNome(attività.getNome());
		

		for (Attività attivitàPassata : utente.getListaAttività()) {
			if (nuovaAttività.getNome().equalsIgnoreCase(attivitàPassata.getNome())) {
				modelAndView.addObject("messaggio", "Un'attività con lo stesso nome è già stata inserita");
				modelAndView.setViewName("utente/agenda");
				return modelAndView;
			}
		}
		
		nuovaAttività.setDataStringa(attivitàService.reversedDateFormat(attività));
		nuovaAttività.setUtente(utente);
		utente.getListaAttività().add(nuovaAttività);
		attivitàService.save(nuovaAttività);
		
		modelAndView.addObject("id", id);
		modelAndView.addObject("listaAttività", utente.getListaAttività());
		modelAndView.addObject("attività", new Attività());
		modelAndView.setViewName("utente/agenda");
		return modelAndView;
	}
	
}
