package brewer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import brewer.model.Usuario;
import brewer.repository.Usuarios;
import brewer.service.exception.EmailJaCadastradoException;
import brewer.service.exception.SenhaUsuarioObrigatoriaException;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> optionalUsuarioByEmail = this.usuarios.findByEmail(usuario.getEmail());
		if (optionalUsuarioByEmail.isPresent()) {
			throw new EmailJaCadastradoException("Email já cadastrado");
		}
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaUsuarioObrigatoriaException("Senha e obrigatoria para usuario novo");
		}
		if (usuario.isNovo()) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
		}
		this.usuarios.save(usuario);
	}

}
