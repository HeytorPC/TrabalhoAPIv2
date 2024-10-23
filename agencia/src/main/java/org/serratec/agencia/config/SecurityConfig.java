package org.serratec.agencia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// http.authorizeHttpRequests(r -> r.anyRequest().authenticated())
		//.httpBasic(Customizer.withDefaults());
		
		http.authorizeHttpRequests(authorize -> authorize
		
				
		.requestMatchers(HttpMethod.GET, "/pedidos").permitAll()
		.requestMatchers(HttpMethod.POST, "/pedidos").hasRole("ADM")
		.requestMatchers(HttpMethod.PUT, "/pedidos").hasRole("ADM")
		.requestMatchers(HttpMethod.DELETE, "/pedidos").hasRole("ADM")
		.requestMatchers(HttpMethod.GET, "/pedidos/{id}").permitAll()
		.requestMatchers(HttpMethod.POST, "/pedidos/{id}").hasRole("ADM")
		.requestMatchers(HttpMethod.PUT, "/pedidos/{id}").hasRole("ADM")
		.requestMatchers(HttpMethod.DELETE, "/pedidos/{id}").hasRole("ADM")
		.requestMatchers(HttpMethod.GET, "/clientes").permitAll()
		.requestMatchers(HttpMethod.POST, "/clientes").hasRole("ADM")
		.requestMatchers(HttpMethod.PUT, "/clientes").hasRole("ADM")
		.requestMatchers(HttpMethod.DELETE, "/clientes").hasRole("ADM")
		.requestMatchers(HttpMethod.GET, "/clientes/{id}").permitAll()
		.requestMatchers(HttpMethod.POST, "/clientes/{id}").hasRole("ADM")
		.requestMatchers(HttpMethod.PUT, "/clientes/{id}").hasRole("ADM")
		.requestMatchers(HttpMethod.DELETE, "/clientes/{id}").hasRole("ADM")	
		.requestMatchers(HttpMethod.GET, "/viagens").permitAll()
		.requestMatchers(HttpMethod.GET, "/viagens/{id}").permitAll()
		.requestMatchers(HttpMethod.PUT, "/viagens").hasRole("ADM")
		.requestMatchers(HttpMethod.PUT, "/viagens/{id}").hasRole("ADM")
		.requestMatchers(HttpMethod.DELETE, "/viagens").hasRole("ADM")
		.requestMatchers(HttpMethod.DELETE, "/viagens/{id}").hasRole("ADM")
		.requestMatchers(HttpMethod.POST, "/viagens/{id}").hasRole("ADM")
		.requestMatchers(HttpMethod.POST, "/viagens").hasRole("ADM")
		.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasRole("ADM"))
	
		
.csrf(csrf -> csrf.disable())
		.httpBasic(Customizer.withDefaults());
		
		 return http.build();
	}
	@Bean
	public InMemoryUserDetailsManager usuariosDetalhes() {
		UserDetails usuario = User.builder()
				.username("gabriel")
				.password(encoder().encode("teste"))
				.roles("ADM").build();
		
		UserDetails usuario2 = User.builder()
				.username("victor hugo")
				.password(encoder().encode("teste"))
				.roles("ADM").build();
		
		UserDetails usuario3 = User.builder()
				.username("heytor")
				.password(encoder().encode("teste"))
				.roles("ADM").build();
		
		UserDetails usuario4 = User.builder()
				.username("guilherme")
				.password(encoder().encode("teste"))
				.roles("ADM").build();
		
		UserDetails usuario5 = User.builder()
				.username("matheus")
				.password(encoder().encode("teste"))
				.roles("ADM").build();
		
		UserDetails usuario6 = User.builder()
				.username("vitor")
				.password(encoder().encode("teste"))
				.roles("ADM").build();
		
		return new InMemoryUserDetailsManager(usuario, usuario2, usuario3, usuario4, usuario5, usuario6);
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
