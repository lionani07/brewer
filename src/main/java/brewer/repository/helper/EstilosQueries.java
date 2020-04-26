package brewer.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import brewer.model.Estilo;
import brewer.repository.filter.EstiloFilter;

public interface EstilosQueries {
	
	Page<Estilo> filtrar(EstiloFilter estiloFilter, Pageable pageable);

}
