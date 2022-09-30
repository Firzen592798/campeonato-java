package br.com.firzen.campeoanto.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.firzen.campeoanto.model.User;
import br.com.firzen.campeoanto.repository.RoleRepository;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController extends AbstractController<User> {
	
	private final String URL_PAGE = "/user";
	
	private RoleRepository roleRepository;
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("userLista", service.findAll());
		return URL_PAGE + URL_LIST;
	}

	@GetMapping(URL_FORM)
	public String form(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roleLista", roleRepository.findAll());
		return URL_PAGE + URL_FORM;
	}

	@GetMapping(URL_EDIT + "/{id}")
	public String edit(@PathVariable Long id, Model model) {
		User user = service.findById(id).orElseThrow(() -> new IllegalArgumentException());
		model.addAttribute("user", user);
		return URL_PAGE + URL_FORM;
	}

	@PostMapping(URL_FORM)
	public String form(@Valid User user, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return URL_PAGE+URL_FORM;
		} else {
			service.save(user);
			return "redirect:";
		}
	}

	@GetMapping(URL_DELETE + "/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes ra) {
		service.deleteById(id);
		return "redirect:/user";
	}
}
