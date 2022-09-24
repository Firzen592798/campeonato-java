package br.com.firzen.campeoanto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.firzen.campeoanto.exceptions.NegocioException;
import br.com.firzen.campeoanto.exceptions.NotFoundException;
import br.com.firzen.campeoanto.model.Campeonato;
import br.com.firzen.campeoanto.model.Participante;
import br.com.firzen.campeoanto.model.Standing;
import br.com.firzen.campeoanto.repository.ParticipanteRepository;
import br.com.firzen.campeoanto.services.CampeonatoService;

@Controller
@RequestMapping("/campeonato")
public class CampeonatoController extends AbstractController<Campeonato> {
	
	private final static String URL_PAGE = "campeonato";
	
	private final static String URL_PARTICIPANTES= "/participantes";
	
	@Autowired
	private ParticipanteRepository participanteRepository;
	
	@GetMapping
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(URL_PAGE + URL_LIST);
		modelAndView.addObject("campeonatoLista", service.findAll());
		//modelAndView.addObject("success", "Salvo com sucesso");
		return modelAndView;
	}
	
	@GetMapping(URL_FORM)
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("campeonato", new Campeonato());
		return modelAndView;
	}
	
	@PostMapping(URL_FORM)
	public ModelAndView save(@Valid Campeonato campeonato, BindingResult br, Model model) {
		if(br.hasErrors()) {
			return new ModelAndView();
		}
		try {
			service.save(campeonato);
			model.addAttribute("success", "Salvo com sucesso");
		} catch (NegocioException e) {
			model.addAttribute("error", e.getMessage());
			return new ModelAndView();
		}
		ModelAndView modelAndView = new ModelAndView(new RedirectView(URL_PAGE));
		return modelAndView;
	}
	
	@GetMapping(URL_PARTICIPANTES)
	public ModelAndView addParticipante(@RequestParam Long id) throws NotFoundException {
		Campeonato campeonato = service.findById(id).orElseThrow(() ->new NotFoundException());
		if(campeonato.getStandings().isEmpty()) {
			campeonato.setNumParticipantes(1);
			campeonato.setStandings(new ArrayList<Standing>());
			for(int i = 0; i < campeonato.getNumParticipantes(); i++) {
				campeonato.addStanding(new Standing());
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("campeonato", campeonato);
		modelAndView.addObject("participanteLista", participanteRepository.findAll());
		return modelAndView;
	}
	
	@PostMapping(URL_PARTICIPANTES)
	public ModelAndView saveParticipantes(@Valid Campeonato campeonato, BindingResult br, RedirectAttributes ra) {
		if(br.hasErrors()) {
			ra.addAttribute("participanteLista", participanteRepository.findAll());
			return new ModelAndView();
		}
		try {
			ModelAndView modelAndView = new ModelAndView(new RedirectView("/"+URL_PAGE+URL_VIEW+"/"+campeonato.getTemporada(), true));
			service.save(campeonato);
			ra.addFlashAttribute("success", "Salvo com sucesso");
			return modelAndView;	
		} catch (NegocioException e) {
			ra.addAttribute("error", e.getMessage());
			ra.addAttribute("participanteLista", participanteRepository.findAll());
			return new ModelAndView();
		}

	}
	
	@PostMapping(value=URL_PARTICIPANTES, params={"addRow"})
	public String addRow(Model model, final Campeonato campeonato, final BindingResult br) {
		model.addAttribute("participanteLista", participanteRepository.findAll());
		Standing s = new Standing();
		s.setPosicao(campeonato.getStandings().size() + 1);
		campeonato.addStanding(s);
		return URL_PAGE+URL_PARTICIPANTES;	
	}
	
	@PostMapping(value=URL_PARTICIPANTES, params={"removeRow"})
	public String removeRow(Model model, final Campeonato campeonato, final BindingResult br, final HttpServletRequest req) {
		model.addAttribute("participanteLista", participanteRepository.findAll());
		final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		campeonato.getStandings().remove(rowId.intValue());
		return URL_PAGE+URL_PARTICIPANTES;	
	}

	@PostMapping(value=URL_PARTICIPANTES, params={"up"})
	public String upRow(Model model, final Campeonato campeonato, final BindingResult br, final HttpServletRequest req) {
		model.addAttribute("participanteLista", participanteRepository.findAll());
		final Integer rowId = Integer.valueOf(req.getParameter("up"));
		Standing aux = campeonato.getStandings().get(rowId);
		campeonato.getStandings().set(rowId, campeonato.getStandings().get(rowId - 1));
		campeonato.getStandings().set(rowId - 1, aux);
		return URL_PAGE+URL_PARTICIPANTES;	
	}

	@PostMapping(value=URL_PARTICIPANTES, params={"down"})
	public String downRow(Model model, final Campeonato campeonato, final BindingResult br, final HttpServletRequest req) {
		model.addAttribute("participanteLista", participanteRepository.findAll());
		final Integer rowId = Integer.valueOf(req.getParameter("down"));
		Standing aux = campeonato.getStandings().get(rowId);
		campeonato.getStandings().set(rowId, campeonato.getStandings().get(rowId + 1));
		campeonato.getStandings().set(rowId + 1, aux);
		return URL_PAGE+URL_PARTICIPANTES;	
	}

	
	@GetMapping(URL_VIEW+"/{temporada}")
	public ModelAndView view(@PathVariable("temporada") Integer temporada) {
	    List<Campeonato> campeonatos = ((CampeonatoService) service).findByTemporada(temporada);
	    ModelAndView ma = new ModelAndView(URL_PAGE+URL_VIEW);
	    ma.addObject("campeonatoLista", campeonatos);
	    return ma;
	}
	
	@GetMapping(URL_DELETE+"{id}")
	public ModelAndView delete(@PathVariable("id") long id) {
		Campeonato user = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid campeonato Id:" + id));
		service.delete(user);
		return new ModelAndView(new RedirectView("/"));
	}
}
