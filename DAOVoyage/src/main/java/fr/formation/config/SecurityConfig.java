package fr.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.formation.security.AuthentificationRedirect;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/commandes/**").hasRole("CLIENT")
		.antMatchers("/cagnotte/**").hasRole("CLIENT")
		.antMatchers("/inscription/**").anonymous()
		.antMatchers("/**").permitAll()
		.and()
		.formLogin()
			.loginPage("/accueil")
			.loginProcessingUrl("/accueil")	//spring qui crée un @PostMapping("/lien")
			.successHandler(new AuthentificationRedirect())	//page par défaut après connexion
			.failureUrl("/accueil?error=true")	//page d'erreur de connexion
			.permitAll()
		.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/accueil")
			.permitAll();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}