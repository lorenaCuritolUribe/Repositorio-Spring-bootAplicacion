package com.lorenacuritol.springbootApp.service;
import javax.validation.Valid;

import com.lorenacuritol.springbootApp.entity.User;
public interface UserService {
	
//creamos un metodo
//	iterable: significa que puede ser cualquier colección que queramos utilizar

	public Iterable<User> getAllUser();

	public User createUser(User user) throws Exception;

	
	
	
}
