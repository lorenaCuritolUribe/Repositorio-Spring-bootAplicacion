package com.lorenacuritol.springbootApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lorenacuritol.springbootApp.entity.User;
import com.lorenacuritol.springbootApp.repository.RoleRepository;
import com.lorenacuritol.springbootApp.service.UserService;

@Controller
public class UserController {
	
//	@Autowired: me va a inyectar dependencias, luego voy a user-list.html para mostrar 
//la lista de usuarios
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserService userService;

	@GetMapping("/")
	public String index() {
		return "index";		
	}
	
	@GetMapping("/userForm")
	public String userForm(Model model){
		model.addAttribute("userForm", new User());
		model.addAttribute("userList", userService.getAllUser());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("listTab","active");
		return "user-form/user-view";
		
	}

}
