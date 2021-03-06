package brewer.repository.helper.cerveja;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import brewer.model.Cerveja;
import brewer.repository.filter.CervejaFilter;

public interface CervejasQueries {
	
	Page<Cerveja> filtrar(CervejaFilter CervejaFilter, Pageable pageable);

}
