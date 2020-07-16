<<<<<<< HEAD
package brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import brewer.model.Usuario;
import brewer.repository.helper.usuarios.UsuariosQueries;

public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {
	
	Optional<Usuario> findByEmail(String email);

}
=======
package brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import brewer.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

}
>>>>>>> parent of 76c6746... Restringindo o acesso às páginas
