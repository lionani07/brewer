package brewer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brewer.model.Usuario;
import brewer.repository.Usuarios;
import brewer.service.exception.EmailJaCadastradoException;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private Usuarios usuarios;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> optionalUsuarioByEmail = this.usuarios.findByEmail(usuario.getEmail());
		if (optionalUsuarioByEmail.isPresent()) {
			throw new EmailJaCadastradoException("Email já cadastrado");
		}
		this.usuarios.save(usuario);
	}

}
