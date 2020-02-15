package brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brewer.model.Estilo;
import brewer.repository.Estilos;

@Service
public class CadastroEstiloService {
	
	@Autowired
	private Estilos estilos;
	
	public void salvar(Estilo estilo) {
		estilos.save(estilo);
	}

}
