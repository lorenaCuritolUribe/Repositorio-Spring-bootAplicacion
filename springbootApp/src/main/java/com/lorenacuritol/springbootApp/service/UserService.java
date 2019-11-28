package com.lorenacuritol.springbootApp.service;
import javax.validation.Valid;

import com.lorenacuritol.springbootApp.entity.User;
public interface UserService {
	
//creamos un metodo
//	iterable: significa que puede ser cualquier colección que queramos utilizar

	public Iterable<User> getAllUser();

	public User createUser(User user) throws Exception;
	
//	parte 6: creamos mètodos para actualizar usuarios luego vamos a UserServiceImpl
	
	public User getUserById(Long id) throws Exception;

	public User updateUser(User user) throws Exception;
	
	public void deleteUser(Long id) throws Exception;
	
}
