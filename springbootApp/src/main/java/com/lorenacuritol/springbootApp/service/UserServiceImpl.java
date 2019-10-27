package com.lorenacuritol.springbootApp.service;

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

}
