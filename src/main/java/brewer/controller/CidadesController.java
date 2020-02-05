package brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cidades")
public class CidadesController {
	
	@GetMapping("/nova")
	public ModelAndView nova() {
		return new ModelAndView("cidade/CadastroCidade");
	}
}
