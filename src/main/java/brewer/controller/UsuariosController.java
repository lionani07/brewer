package brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import brewer.model.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		return new ModelAndView("usuario/CadastroUsuario");
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result) {
		if (result.hasErrors()) {
			return novo(usuario);
		}
		System.out.println("data" + usuario.getDataNascimento());
		return new ModelAndView("redirect:/usuarios/novo");
	}
	
}
