package com.lorenacuritol.springbootApp.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lorenacuritol.springbootApp.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

//	creo un metodo para publicar una lista de ususario
//	findBy: significa "encuentre por nombre de usuario"
//public Set<User> findByUsername(String username);
//findByUsername: encuentre por nombre usuario
	public Optional<User> findByUsername(String username);

}
