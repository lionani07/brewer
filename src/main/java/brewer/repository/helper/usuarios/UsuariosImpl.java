package brewer.repository.helper.usuarios;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import brewer.model.Usuario;
import brewer.repository.filter.UsuarioFilter;
import brewer.repository.paginacao.PaginacaoUtil;

public class UsuariosImpl implements UsuariosQueries{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

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

	@Transactional(readOnly = true)
	@SuppressWarnings({ "deprecation", "unchecked"})
	@Override	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Usuario.class);
		
		paginacaoUtil.preparar(criteria, pageable);		
		adicionarFiltro(filtro, criteria);		
		return new PageImpl<>(criteria.list(), pageable, totalFiltro(filtro));
	}
	
	private void adicionarFiltro(UsuarioFilter filtro, Criteria criteria) {
		if(!Objects.isNull(filtro)) {			
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			if(!StringUtils.isEmpty(filtro.getEmail())) {
				criteria.add(Restrictions.ilike("email", filtro.getEmail(), MatchMode.ANYWHERE));
			}
		}		
	}
	
	@SuppressWarnings("deprecation")
	private Long totalFiltro(UsuarioFilter filtro) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Usuario.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	

}

