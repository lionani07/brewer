package brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import brewer.model.Estilo;
import brewer.service.CadastroEstiloService;

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
		if(result.hasErrors()) {
			return novo(estilo);
		}
		cadastroEstiloService.salvar(estilo);
		flash.addFlashAttribute("msgSuccess", "Estilo cadastrado com suceso!");
		return new ModelAndView("redirect:/estilos/novo");
		
	}

}
