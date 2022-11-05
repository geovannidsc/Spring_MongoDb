package com.estudo.springmongo.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudo.springmongo.domain.Post;
import com.estudo.springmongo.domain.User;
import com.estudo.springmongo.domain.dto.UserDTO;
import com.estudo.springmongo.service.PostService;
import com.estudo.springmongo.service.UserService;

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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO objDto) {

		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(obj.getId()).toUri();
	  
		return ResponseEntity.created(uri).build();
	}
//	
//	
//	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
//	public ResponseEntity<Void> delete(@PathVariable String id) {
//
//		service.delete(id);
//		return ResponseEntity.noContent().build();
//	}
//	
//	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
//	public ResponseEntity<UserDTO> update(@RequestBody UserDTO objDto, @PathVariable String id) {
//
//		User obj = service.fromDTO(objDto);
//		obj.setId(id);
//		obj = service.update(obj);
//	  
//		return ResponseEntity.noContent().build();
//	}
//	
//	
//	@RequestMapping(value = "/{id}/posts",method = RequestMethod.GET)
//	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
//
//		User obj = service.findById(id);
//		
//		return ResponseEntity.ok().body(obj.getPosts());
//	}
	
	
	
}
