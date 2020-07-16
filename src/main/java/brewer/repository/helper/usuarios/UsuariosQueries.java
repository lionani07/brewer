package brewer.repository.helper.usuarios;

import java.util.List;
import java.util.Optional;

import brewer.model.Usuario;

public interface UsuariosQueries {
	
	Optional<Usuario> porEmailEAtivo(String email);
	List<String> permissoes(Usuario usuario);

}

