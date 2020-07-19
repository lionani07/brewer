package brewer.repository.helper.usuarios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import brewer.model.Usuario;
import brewer.repository.filter.UsuarioFilter;

public interface UsuariosQueries {
	
	Optional<Usuario> porEmailEAtivo(String email);
	List<String> permissoes(Usuario usuario);
	Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);

}
