package brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vendas")
public class VendaController {
	
	@GetMapping("/nova")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView("venda/CadastroVenda");
		return mv;
	}

}
