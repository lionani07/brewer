package brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import brewer.model.Estilo;
import brewer.service.CadastroEstiloService;
import brewer.service.exception.NomeEstiloJaCadastradoException;

@Controller
@RequestMapping("/estilos")
public class EstilosController {

	@Autowired
	private CadastroEstiloService cadastroEstiloService;

	@GetMapping("/novo")
	public ModelAndView novo(Estilo estilo) {
		return new ModelAndView("estilo/CadastroEstilo");
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Estilo estilo, BindingResult result, RedirectAttributes flash) {
		if (result.hasErrors()) {
			return novo(estilo);
		}
		try {
			cadastroEstiloService.salvar(estilo);
		} catch (NomeEstiloJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}
		flash.addFlashAttribute("msgSuccess", "Estilo cadastrado com suceso!");
		return new ModelAndView("redirect:/estilos/novo");

	}

	@RequestMapping(value = "/cadastroRapido", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> cadastroRapido(@RequestBody @Valid Estilo estilo, BindingResult result) {		
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}		
		try {
			estilo = cadastroEstiloService.salvar(estilo);
		} catch (NomeEstiloJaCadastradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}		
		return ResponseEntity.ok(estilo);
	}

}
