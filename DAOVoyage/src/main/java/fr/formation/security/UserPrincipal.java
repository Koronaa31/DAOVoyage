package fr.formation.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.formation.model.Utilisateur;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Utilisateur utilisateur;
	
	public UserPrincipal(Utilisateur utilisateur) {
		if (utilisateur == null) {
			throw new UsernameNotFoundException("Le utilisateur n'existe pas");
		}
		this.utilisateur = utilisateur;
	}
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<>();
		
		if (this.utilisateur.getTypeCompte().contentEquals("Admin")) {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		if (this.utilisateur.getTypeCompte().contentEquals("Client")) {
			roles.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
		}
		
		return roles;
	}

	@Override
	public String getPassword() {
		return this.utilisateur.getPassword();
	}

	@Override
	public String getUsername() {
		return this.utilisateur.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}