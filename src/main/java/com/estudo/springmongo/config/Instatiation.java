package com.estudo.springmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.estudo.springmongo.domain.User;
import com.estudo.springmongo.repository.UserRepository;



@Configuration
public class Instatiation implements CommandLineRunner{

	@Autowired
	private UserRepository userReposiory;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		userReposiory.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		

		userReposiory.saveAll(Arrays.asList(maria,alex,bob));
	}
 
	
	
	
}
