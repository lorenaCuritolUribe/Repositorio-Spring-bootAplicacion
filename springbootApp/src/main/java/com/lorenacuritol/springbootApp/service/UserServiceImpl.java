package com.lorenacuritol.springbootApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorenacuritol.springbootApp.entity.User;
import com.lorenacuritol.springbootApp.repository.UserRepository;

//service: comentamos que esta clase es un servicio
@Service
//se utiliza 1 interfaz para acceder a los servicios
//implements USerService: Implemento el servicio
public class UserServiceImpl implements UserService {
	
//vamos a userService y crearemos la implementación del método creado abajo
	
//	@Autowrite: con esta anotaciòn le decimos a la inyecciòn de dependencias de spring que 
//se encargue de traernos esos recursos
	@Autowired
	UserRepository repositorio;
	
	@Override
	public Iterable<User> getAllUser() {
//entonces le decimos que retorne todos los usuario		
		return repositorio.findAll();
	}
	
	private boolean checkUsernameAvailable(User user) throws Exception {
	
		Optional<User> userFound = repositorio.findByUsername(user.getUsername());
		if(userFound.isPresent()) {
			throw new Exception("usuario no disponible");
			
		}
		return true;
	}
	
	private boolean checkPasswordValid(User user) throws Exception{
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y confirmaciòn de pasword no son iguales");
		}
		return true;			
	}

	@Override
	public User createUser(User user) throws Exception{
		// creamos un if para saber si el username y password existen
		if(checkUsernameAvailable(user) && checkPasswordValid(user)) {
			user= repositorio.save(user);
		}
		return user;
	}

}
