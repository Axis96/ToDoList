package it.dstech.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import it.dstech.model.Utente;
import it.dstech.service.UtenteService;

@Controller
public class RegistrazioneController {
	@Autowired
	private UtenteService utenteService;


	@GetMapping(value = "/registrazione")
	public ModelAndView registrazione() {
		ModelAndView modelAndView = new ModelAndView();
		Utente utente = new Utente();
		modelAndView.addObject("utente", utente);
		modelAndView.setViewName("registrazione");
		return modelAndView;

	}

	@PostMapping(value = "/registrazioneUtente")
	public ModelAndView registrazioneDocente(@Valid Utente utenteDaControllare, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Utente userExists = utenteService.findUserByUsername(utenteDaControllare.getUsername());
		
		if (userExists != null) {
			bindingResult.rejectValue("username", "error.user", "Utente già registrato");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registrazione");
		} else {
			utenteService.save(utenteDaControllare);
			modelAndView.addObject("successMessage", "Utente registrato con successo!");
		}
		
		Utente nuovoUtente = new Utente();
		
		modelAndView.addObject("utente", nuovoUtente);
		modelAndView.setViewName("registrazione");
		return modelAndView;
	}
	
}