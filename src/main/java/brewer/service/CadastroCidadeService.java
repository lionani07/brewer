package brewer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brewer.model.Cidade;
import brewer.repository.Cidades;
import brewer.service.exception.NomeCidadeJaCadastradoException;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private Cidades cidades;
	
	@Transactional
	public void salvar(Cidade cidade) {
		Optional<Cidade> optionalCidade = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		if (optionalCidade.isPresent()) {
			throw new NomeCidadeJaCadastradoException("Nome de Cidade já cadastrado");
		}
		cidades.save(cidade);
	}

}
