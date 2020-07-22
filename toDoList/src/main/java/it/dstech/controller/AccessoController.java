package it.dstech.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import it.dstech.model.Attività;
import it.dstech.model.Utente;
import it.dstech.service.UtenteService;

@Controller
public class AccessoController {
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping(value = { "/", "/login" })
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homepage");
		return modelAndView;
	}
	
	@GetMapping(value = "/utente/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Utente utente = utenteService.findUserByUsername(auth.getName());
		
		
		modelAndView.addObject("idUtente", utente.getIdUtente());
		modelAndView.setViewName("utente/home");
		modelAndView.addObject("listaAttività", utente.getListaAttività());
		modelAndView.addObject("attività", new Attività());
		return modelAndView;
		
	}
	
}