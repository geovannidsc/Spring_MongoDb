package com.estudo.springmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudo.springmongo.domain.Post;
import com.estudo.springmongo.repository.PostRepository;
import com.estudo.springmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public List<Post> findAll() {

		return repo.findAll();
	}

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public Post insert(Post obj) {

		return repo.insert(obj);
	}

	public void delete(String id) {

		findById(id);
		repo.deleteById(id);

	}

	public Post update(Post obj) {
		Post newObj = findById(obj.getId());
		updateData(newObj, obj);

		return repo.save(newObj);
	}

	public void updateData(Post newObj, Post obj) {

		newObj.setTitle(obj.getTitle());
		newObj.setBody(obj.getBody());

	}
	
	
	public List<Post> findByTitle(String text){
		
		return repo.findByTitleContainingIgnoreCase(text);
	}
	

}
