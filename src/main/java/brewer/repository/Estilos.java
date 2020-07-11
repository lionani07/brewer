package brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import brewer.model.Estilo;
import brewer.repository.helper.estilos.EstilosQueries;

public interface Estilos extends JpaRepository<Estilo, Long>, EstilosQueries{
	
	public Optional<Estilo> findByNomeIgnoreCase(String nome);

}
