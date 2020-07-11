package brewer.repository.helper.cidades;

import java.util.Objects;

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

import brewer.model.Cidade;
import brewer.repository.filter.CidadeFilter;
import brewer.repository.paginacao.PaginacaoUtil;

public class CidadesImpl implements CidadesQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@Transactional(readOnly = true)
	@SuppressWarnings({ "deprecation", "unchecked"})
	@Override
	public Page<Cidade> filtrar(CidadeFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cidade.class);
		
		paginacaoUtil.preparar(criteria, pageable);		
		adicionarFiltro(filtro, criteria);		
		return new PageImpl<>(criteria.list(), pageable, totalFiltro(filtro));
	}
	
	private void adicionarFiltro(CidadeFilter filtro, Criteria criteria) {
		if(!Objects.isNull(filtro)) {			
			if (isFilterEstado(filtro)) {
				criteria.add(Restrictions.eq("estado", filtro.getEstado()));
			}
			
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}			
		}		
	}
	
	@SuppressWarnings("deprecation")
	private Long totalFiltro(CidadeFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cidade.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	
	private boolean isFilterEstado(CidadeFilter filtro) {
		return Objects.nonNull(filtro.getEstado()) && Objects.nonNull(filtro.getEstado().getCodigo());
	}

	
	
}
