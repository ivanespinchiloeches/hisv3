package com.ivan.thehealthsoftwarecompany.hisv3.controller.request;

import com.ivan.thehealthsoftwarecompany.hisv3.data.dto.UserDTO;
import com.ivan.thehealthsoftwarecompany.hisv3.data.dto.mapper.UserToUserDTO;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.Role;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.User;
import com.ivan.thehealthsoftwarecompany.hisv3.exception.UsernameExistsException;
import com.ivan.thehealthsoftwarecompany.hisv3.service.RolesService;
import com.ivan.thehealthsoftwarecompany.hisv3.service.UserService;
import com.ivan.thehealthsoftwarecompany.hisv3.service.impl.RolesServiceImpl;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class AppController {
	//@Autowired
	private final UserService service;
	private final RolesService rolesService;
	private final UserToUserDTO userToUserDTO;

	public AppController(UserService service, UserToUserDTO userToUserDTO, RolesServiceImpl rolesService) {
		this.service = service;
        this.userToUserDTO = userToUserDTO;
		this.rolesService = rolesService;
    }

	@SuppressWarnings("SameReturnValue")
    @RequestMapping({"/","/list"})
	public String viewHomePage(Model model) {
		List<User> listUsers = service.findAllPlainUsers();
		model.addAttribute("listUsers", listUsers);
		
		return "index1";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable Long id) {

		ModelAndView mav = new ModelAndView("userapp_form");
		User user = service.getUserById(id);
		UserDTO userDTO = userToUserDTO.transform(user);
		mav.addObject("user", userDTO);

		Sort sortByNameASC = Sort.by(Sort.Direction.ASC, "name");
		List<Role> listOfRoles = rolesService.findAll(sortByNameASC);
		mav.addObject("listOfRoles", listOfRoles);

		return mav;

	}

	@SuppressWarnings("SameReturnValue")
    @RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		service.deleteUser(id);

		return "redirect:/users/";
	}



	@PostMapping("/save")
	public ModelAndView registerUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult) {

		ModelAndView mav ;
		if (bindingResult.hasErrors()) {
			mav = new ModelAndView("userapp_form");

			Sort sortByNameASC = Sort.by(Sort.Direction.ASC, "name");
			List<Role> listOfRoles = rolesService.findAll(sortByNameASC);
			mav.addObject("listOfRoles", listOfRoles);

			mav.addObject("user", user);
			return mav;
		}

		try {

			service.saveUser(user);

			mav = new ModelAndView("redirect:/users/list");
			return mav;
		}
		catch (DataIntegrityViolationException e) {
			throw new UsernameExistsException("Username already exists");
		}

	}


	@RequestMapping("/new")
	public ModelAndView showNewProductForm(@ModelAttribute("user") UserDTO user) {

		ModelAndView mav = new ModelAndView("userapp_form");

		UserDTO userDTO = new UserDTO();
		mav.addObject("user", userDTO);

		Sort sortByNameASC = Sort.by(Sort.Direction.ASC, "name");
		List<Role> listOfRoles = rolesService.findAll(sortByNameASC);
		mav.addObject("listOfRoles", listOfRoles);

		return mav;
	}
	



}
