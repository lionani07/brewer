package brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import brewer.model.Cerveja;
import brewer.repository.helper.cerveja.CervejasQueries;

public interface Cervejas extends JpaRepository<Cerveja, Long>, CervejasQueries {	

}
