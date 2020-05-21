package brewer.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import brewer.model.Cliente;
import brewer.repository.filter.ClienteFilter;

public interface ClientesQueries {
	
	Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);

}
