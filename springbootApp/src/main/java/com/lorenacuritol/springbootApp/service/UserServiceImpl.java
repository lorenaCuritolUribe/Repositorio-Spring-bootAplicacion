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
//método para verificar si password viene vacìo
		if(user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty() ) {
			throw new Exception("confirmar la contraseña es obligatorio");
		}
		
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

	@Override
	public User getUserById(Long id) throws Exception {
		return repositorio.findById(id).orElseThrow(()-> new Exception("El usuario para editar no existe"));
	}
//mapeo: es pasar los objetos o los valores de los usuarios que tenemos en  el formulario a la bd
	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser = getUserById(fromUser.getId());
//fromUser(desde este usuario), toUser(hasta este usuario)
		mapUser(fromUser, toUser);
		return repositorio.save(toUser);
	}
//metodo para mapear los datos que dice de què usuario a cuàl usuario
	
	protected void mapUser(User from, User to) {
		
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
		to.setPassword(from.getPassword());
		
	}

}
