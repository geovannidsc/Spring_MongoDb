package com.estudo.springmongo.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudo.springmongo.domain.Post;
import com.estudo.springmongo.resource.util.URL;
import com.estudo.springmongo.service.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findAll() {

		List<Post> list = postService.findAll();
		//List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {

		Post obj = postService.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/titlesearch",method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text) {

		text =  URL.decodeParam(text);
		List<Post> list = postService.findByTitle(text);
		
		
		return ResponseEntity.ok().body(list);
	}
	
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Post> insert(@RequestBody Post obj) {

		Post post = postService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(post.getId()).toUri();
	  
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {

		postService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Post> update(@RequestBody Post obj, @PathVariable String id) {


		obj.setId(id);
		obj = postService.update(obj);
	  
		return ResponseEntity.noContent().build();
	}

	
	
	
}
