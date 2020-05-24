package brewer.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import brewer.model.Cidade;
import brewer.repository.filter.CidadeFilter;

public interface CidadesQueries {
	
	Page<Cidade> filtrar(CidadeFilter cidadeFilter, Pageable pageable);

}
