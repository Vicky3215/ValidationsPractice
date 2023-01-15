package com.pract.config;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectWebConfig {

	@Bean
	SecurityFilterChain defauSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().ignoringRequestMatchers("/api/**").and().authorizeHttpRequests().requestMatchers("/about").authenticated()
		.requestMatchers("/login").permitAll()
		.requestMatchers("/logout").permitAll()
		.requestMatchers("/api/**").permitAll()
		.requestMatchers("/addlogin").permitAll()
		.requestMatchers("/displayClasses").permitAll()
		.requestMatchers("/saveMsg").permitAll()
		.requestMatchers("/addClasss").permitAll() 
		.requestMatchers("/addClass").permitAll()
		.requestMatchers("/deleteStudent/**").permitAll()
		.requestMatchers("/deleteClass/**").permitAll()
		.requestMatchers("/addnewCourse").permitAll()
		.requestMatchers("/viewStudents/**").permitAll()
		.requestMatchers("/addNewStudentInthiscouse/**").permitAll()
		.requestMatchers("/deleteStudentfomthisCourse/**").permitAll()
		.requestMatchers("/disCourses").permitAll()
		.requestMatchers("/addStudent").permitAll()
		.requestMatchers("/displayStudents/**").permitAll()
		.requestMatchers("/home").permitAll()
		.requestMatchers("/").permitAll()
		.requestMatchers("/dashboad").permitAll()
		.requestMatchers("/loginO").permitAll()
		.and().formLogin().loginPage("/loginO").defaultSuccessUrl("/dashboad").failureUrl("/loginO?error=true").permitAll()
		.and().logout().logoutUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
		.and().httpBasic();
		
		return http.build();
		
	}
	/*
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user=User.withDefaultPasswordEncoder()
				.username("abdaal")
				.password("The@1234")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user);
	}
	*/
	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
}
