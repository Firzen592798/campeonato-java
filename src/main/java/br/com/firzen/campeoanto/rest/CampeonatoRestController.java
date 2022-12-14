package br.com.firzen.campeoanto.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.firzen.campeoanto.exceptions.NotFoundException;
import br.com.firzen.campeoanto.model.Campeonato;
import br.com.firzen.campeoanto.services.CampeonatoService;

@Controller
@RequestMapping("rest/campeonato")
public class CampeonatoRestController {
	
	@Autowired
	private CampeonatoService campeonatoService;
	
	@GetMapping("/list")
	public @ResponseBody ResponseEntity<Iterable<Campeonato>> list(){
		return ResponseEntity.ok(campeonatoService.findAll());
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Campeonato> view(@PathVariable Long id){
		return campeonatoService.findById(id).map(ResponseEntity::ok)
				//.map( cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}
}
