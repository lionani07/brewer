package brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import brewer.model.Cliente;
import brewer.repository.helper.clientes.ClientesQueries;

public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries{

	Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

}
