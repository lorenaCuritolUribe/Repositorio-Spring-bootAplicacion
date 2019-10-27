package com.lorenacuritol.springbootApp.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.lorenacuritol.springbootApp.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

//	creo un metodo para publicar una lista de ususario
//	findBy: significa "encuentre por nombre de usuario"
//public Set<User> findByUsername(String username);

}
