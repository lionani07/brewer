package brewer.repository;

import brewer.model.Estilo;
import brewer.repository.helper.estilos.EstilosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Estilos extends JpaRepository<Estilo, Long>, EstilosQueries{
	
	public Optional<Estilo> findByNomeIgnoreCase(String nome);

}
