package brewer.repository.helper.usuarios;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import brewer.model.Usuario;

public class UsuariosImpl implements UsuariosQueries{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Usuario> porEmailEAtivo(String email) {
		String sql = "select u from Usuario u where lower(u.email) = lower(:email) and u.ativo = true";		
		Usuario usuario =  this.entityManager
				.createQuery(sql, Usuario.class)
				.setParameter("email", email)
				.getSingleResult();
		
		return Optional.ofNullable(usuario);
		
	}

}
