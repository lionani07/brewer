package brewer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brewer.model.Cliente;
import brewer.repository.Clientes;
import brewer.service.exception.CpfCnpjClienteJaCadastradoException;

@Service
public class CadastroClienteService {
	
	@Autowired
	private Clientes clientes;
	
	@Transactional
	public void salvar(Cliente cliente) {		
		Optional<Cliente> clienteExistente = clientes.findByCpfOuCnpj(cliente.getCpfOuCnpjSemFormatacao());
		
		if (clienteExistente.isPresent()) {
			throw new CpfCnpjClienteJaCadastradoException("CPF/CNPJ já cadastrado");
		}		
		this.clientes.save(cliente);
	}
	
	

}
