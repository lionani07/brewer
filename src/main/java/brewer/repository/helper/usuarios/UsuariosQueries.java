package brewer.repository.helper.usuarios;

import java.util.Optional;

import brewer.model.Usuario;

public interface UsuariosQueries {
	
	Optional<Usuario> porEmailEAtivo(String email);

}
