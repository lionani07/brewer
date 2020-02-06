package brewer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import brewer.model.Cerveja;

@Controller
@RequestMapping("/cervejas")
public class CervejasController {
	
	private static final Logger logger = LoggerFactory.getLogger(CervejasController.class);
	
	@GetMapping("/novo")
	public ModelAndView novo(Cerveja cerveja) {
		logger.info("teste nivel info");
		return new ModelAndView("cerveja/CadastroCerveja");
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {		
		if(result.hasErrors()) {
			return novo(cerveja);
		}
		System.out.println("Cadastrar cerceja!" + cerveja.getSku());			
		attributes.addFlashAttribute("msg", "Cerveja cadastrada com succeso");
		return new ModelAndView("redirect:/cervejas/novo");
	}
	
}
