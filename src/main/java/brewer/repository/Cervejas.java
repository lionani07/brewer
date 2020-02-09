package brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import brewer.model.Cerveja;

public interface Cervejas extends JpaRepository<Cerveja, Long> {	

}
