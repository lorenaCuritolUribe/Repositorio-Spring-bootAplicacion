package com.lorenacuritol.springbootApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lorenacuritol.springbootApp.entity.User;
import com.lorenacuritol.springbootApp.repository.RoleRepository;
import com.lorenacuritol.springbootApp.service.UserService;

@Controller
public class UserController {

//	@Autowired: me va a inyectar dependencias, luego voy a user-list.html para mostrar 
//la lista de usuarios

// aca instanciamos roles mediante @Autowired para más abajo llamarlos
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/userForm")
	public String userForm(Model model) {
//userForm: Lo utiliza el formulario de creación de usuario.
		model.addAttribute("userForm", new User());
//userList: Lo utilizaremos para mostrar la lista de usuario en el DataTable
//estos datos los invocamos en el user-form.html con th para llamarlos
		model.addAttribute("userList", userService.getAllUser());
//roles: Mostrara la lista de roles disponibles en el formulario
		model.addAttribute("roles", roleRepository.findAll());
//listTab:Indica que la pestaña list sera la que este activa.
		model.addAttribute("listTab", "active");
		return "user-form/user-view";

	}

//@PostMapping: Indica que para acceder a este metodo debe ser utilizando un llamado POST y no GET, ademas que la ruta debe de ser /userForm
//@Valid: Indica Spring que verifique los atributos del entity, en este caso @NotBlank y @Email.
//@ModelAttribute: El constructor de esta anotacion recibe el nombre del formulario html y lo convierte a un objeto Java.
//BindingResult: Contendra la informacion del resultado entre el mapeo del formulario html y el objeto Java User.
//creamos un mètodo controlador
	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("userForm") User user, BindingResult result, ModelMap model)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab", "active");
		}
//si las validaciones estan correctas las guardamos en la bd con else
		else {
			try {
				userService.createUser(user);
//aca le decimos que si entra en la excepcion muestre el nuevo usuario en las lista (listTAb)
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab", "active");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUser());
				model.addAttribute("roles", roleRepository.findAll());
			}
		}
		model.addAttribute("userList", userService.getAllUser());
		model.addAttribute("roles", roleRepository.findAll());
		return "user-form/user-view";
	}

	@GetMapping("//editUser/{id}")
	public String geteditUserForm(Model model, @PathVariable(name = "id") Long id) throws Exception {
//al instanciar al usuario, lo capturamos con su id, pasándosele con el id del url 
		User userToEdit = userService.getUserById(id);

		// aqui mandamos lo mismo que el formulario
		model.addAttribute("userForm", userToEdit);
		model.addAttribute("userList", userService.getAllUser());
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("formTab", "active");
		model.addAttribute("editMode", "true");

		return "user-form/user-view";

	}

	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm") User user, BindingResult result, ModelMap model) {
//si hay errorres, enviame al userform, activa el formtab y el editmode
		if (result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab", "active");
			model.addAttribute("editMode", "true");

		}
//si las validaciones estan correctas las guardamos en la bd con else
//si no hay errores, actualiza el usuario
		else {
			try {
				userService.updateUser(user);
//aca le decimos que si entra en la excepcion muestre el nuevo usuario en las lista (listTAb)
				model.addAttribute("userForm", new User());// si todo esta bien limpiame la pantalla para un nuevo
															// usuario en el formulario
				model.addAttribute("listTab", "active");
				
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab", "active");
				model.addAttribute("userList", userService.getAllUser());
				model.addAttribute("roles", roleRepository.findAll());
				model.addAttribute("editMode", "true");
			}
		}
		model.addAttribute("userList", userService.getAllUser());
		model.addAttribute("roles", roleRepository.findAll());
		return "user-form/user-view";

	}

	@GetMapping("/userForm/cancelar")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/userForm";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name="id")Long id) {
		try {
			userService.deleteUser(id);
		}catch(Exception e){
			model.addAttribute("listErrorMessage", e.getMessage());
					
		}
//al retornar userForm, le mandamos todos los datos de @PostMapping("/userForm")
		return userForm(model);
		
	}
}