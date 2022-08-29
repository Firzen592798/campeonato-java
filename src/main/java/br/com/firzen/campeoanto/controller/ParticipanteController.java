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

import br.com.firzen.campeoanto.model.Participante;

@Controller
@RequestMapping("/participante")
public class ParticipanteController extends AbstractController<Participante> {

	private final static String URL_PAGE = "participante";

	@GetMapping
	public String list(Model model) {
		model.addAttribute("participanteLista", service.findAll());
		return URL_PAGE + URL_LIST;
	}

	@GetMapping(URL_FORM)
	public String form(Model model) {
		model.addAttribute("participante", new Participante());
		return URL_PAGE + URL_FORM;
	}

	@GetMapping(URL_EDIT + "/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Participante participante = service.findById(id).orElseThrow(() -> new IllegalArgumentException());
		model.addAttribute("participante", participante);
		return URL_PAGE + URL_FORM;
	}

	@PostMapping(URL_FORM)
	public String form(@Valid Participante participante, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return URL_FORM;
		} else {
			service.save(participante);
			return "redirect:";
		}
	}

	@GetMapping(URL_DELETE + "/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes ra) {
		service.deleteById(id);
		return "redirect:/participante";
	}
}
