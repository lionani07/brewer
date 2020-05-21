package brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import brewer.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long> {

}
