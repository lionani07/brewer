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

import brewer.model.Usuario;
import brewer.service.CadastroUsuarioService;
import brewer.service.exception.EmailJaCadastradoException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		return new ModelAndView("usuario/CadastroUsuario");
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(usuario);
		}
		try {
			this.cadastroUsuarioService.salvar(usuario);			
		} catch (EmailJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		attributes.addFlashAttribute("msgSuccess", "Usuario cadastrado com succeso");
		return new ModelAndView("redirect:/usuarios/novo");
	}
	
}
