package com.lorenacuritol.springbootApp.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lorenacuritol.springbootApp.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
