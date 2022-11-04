package com.estudo.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.estudo.springmongo.domain.Post;
import com.estudo.springmongo.domain.User;
import com.estudo.springmongo.repository.PostRepository;
import com.estudo.springmongo.repository.UserRepository;



@Configuration
public class Instatiation implements CommandLineRunner{

	@Autowired
	private UserRepository userReposiory;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYY");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userReposiory.deleteAll();
		postRepository.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("21/03/2020"), "Partir Viagem", "Estou viajando Galera",maria);
		Post post2 = new Post(null, sdf.parse("21/04/2015"), "Ferias Finalmente", "Saindo de Ferias, agora!!", maria);

		userReposiory.saveAll(Arrays.asList(maria,alex,bob));
		postRepository.saveAll(Arrays.asList(post1,post2));
	}
 
	
	
	
}
