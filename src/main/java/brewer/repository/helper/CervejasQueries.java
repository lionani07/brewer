package brewer.repository.helper;

import java.util.List;

import brewer.model.Cerveja;
import brewer.repository.filter.CervejaFilter;

public interface CervejasQueries {
	
	List<Cerveja> filtrar(CervejaFilter CervejaFilter);

}
