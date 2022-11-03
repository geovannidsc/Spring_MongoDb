package com.estudo.springmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudo.springmongo.domain.User;
import com.estudo.springmongo.domain.dto.UserDTO;
import com.estudo.springmongo.repository.UserRepository;
import com.estudo.springmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> finAll(){
		return repo.findAll();
		
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		}
	
	
	public User insert(User obj) {
		
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		
		findById(id);
		repo.deleteById(id);
		
	}
	
	public User fromDTO(UserDTO objDto) {
		
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
	}
	
	
}
