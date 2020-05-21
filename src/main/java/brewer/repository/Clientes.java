package brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import brewer.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

}
