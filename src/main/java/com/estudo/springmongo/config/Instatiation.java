package com.estudo.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.estudo.springmongo.domain.Post;
import com.estudo.springmongo.domain.User;
import com.estudo.springmongo.domain.dto.AuthorDTO;
import com.estudo.springmongo.domain.dto.CommentDTO;
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
		
		userReposiory.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2020"), "Partir Viagem", "Estou viajando Galera",new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("21/04/2015"), "Ferias Finalmente", "Saindo de Ferias, agora!!", new AuthorDTO(maria));

		
		CommentDTO c1 = new CommentDTO("Boa viagem mano", sdf.parse("21/03/2021"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("10/01/2022"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um otimo Dia", sdf.parse("30/08/2011"), new AuthorDTO(alex));

		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userReposiory.save(maria);
	}
 
	
	
	
}
