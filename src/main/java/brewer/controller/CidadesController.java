package brewer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import brewer.controller.page.PageWrapper;
import brewer.model.Cidade;
import brewer.repository.Cidades;
import brewer.repository.Estados;
import brewer.repository.filter.CidadeFilter;
import brewer.service.CadastroCidadeService;
import brewer.service.exception.NomeCidadeJaCadastradoException;

@Controller
@RequestMapping("/cidades")
public class CidadesController {
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private Cidades cidades;
	
	@GetMapping("/nova")
	public ModelAndView nova(Cidade cidade) {
		ModelAndView mv = new ModelAndView("cidade/CadastroCidade");
		mv.addObject("estados", estados.findAll());
		return mv;
	}
	
	@PostMapping("/nova")
	@CacheEvict(value = "cidades", key = "#cidade.estado.codigo", condition = "#cidade.temEstado()")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(cidade);
		}
		try {
			this.cadastroCidadeService.salvar(cidade);
		} catch (NomeCidadeJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(cidade);
		}
		attributes.addFlashAttribute("msgSuccess", "Cidade Cadastrada com succeso");
		return new ModelAndView("redirect:/cidades/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CidadeFilter cidadeFilter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cidade/PesquisaCidade");
		mv.addObject("estados", estados.findAll());
		
		Page<Cidade> pageCidade = cidades.filtrar(cidadeFilter, pageable);
		PageWrapper<Cidade> paginaWrapper = new PageWrapper<>(pageCidade, httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@Cacheable(value = "cidades", key = "#codigoEstado")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> findByEstadoCodigo(@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) {		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {			
		}		
		return cidades.findByEstadoCodigo(codigoEstado); 
	}
	
	
}
