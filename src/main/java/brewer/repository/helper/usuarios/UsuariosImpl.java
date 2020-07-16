<<<<<<< HEAD
package brewer.repository.helper.usuarios;

import java.util.List;
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

	@Override
	public List<String> permissoes(Usuario usuario) {
		String sql = "select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario";
		
		List<String> permissoes = this.entityManager
				.createQuery(sql, String.class)
				.setParameter("usuario", usuario).getResultList();		
		
		return permissoes;
	}

}
=======
package brewer.repository.helper.usuarios;

import java.util.List;
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

	@Override
	public List<String> permissoes(Usuario usuario) {
		String sql = "select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario";
		return this.entityManager
				.createQuery(sql, String.class)
				.setParameter("usuario", usuario)
				.getResultList();
	}

}
>>>>>>> 8ca0fb5bd23074cc317bc96de7af1620ad5a4a7a
