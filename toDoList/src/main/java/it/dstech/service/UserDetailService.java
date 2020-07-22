package it.dstech.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.dstech.model.Utente;
import it.dstech.model.Ruolo;

@Service
public class UserDetailService implements UserDetailsService{

    @Autowired
    private UtenteService utenteService;


    @Override
    @Transactional
	public UserDetails loadUserByUsername(String username)  {
		Utente utente = utenteService.findUserByUsername(username);
		List<GrantedAuthority> authorities = getUserAuthority(utente.getRuolo());
				 
		return buildUserForAuthentication(utente, authorities);
	}


   private List<GrantedAuthority> getUserAuthority(Set<Ruolo> userRoles) {
       Set<GrantedAuthority> roles = new HashSet<>();
       for (Ruolo role : userRoles) {
           roles.add(new SimpleGrantedAuthority(role.getRuolo()));
       }
       return new ArrayList<>(roles);
   }

   private UserDetails buildUserForAuthentication(Utente utente, List<GrantedAuthority> authorities) {
       return new org.springframework.security.core.userdetails.User(utente.getUsername(), utente.getPassword(),
               utente.getActive(), true, true, true, authorities);
   }

}

