package brewer.repository.filter;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import brewer.model.Cerveja;
import brewer.repository.helper.CervejasQueries;

public class CervejasImpl implements CervejasQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional(readOnly = true)
	@SuppressWarnings({ "deprecation", "unchecked"})
	@Override
	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable) {			
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		criteria.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		criteria.setMaxResults(pageable.getPageSize());
		
		Sort sort = pageable.getSort();
		if (!Objects.isNull(sort)) {
			Sort.Order sortOrder = sort.iterator().next();
			String property = sortOrder.getProperty();			
			criteria.addOrder(sortOrder.isAscending() ? Order.asc(property) : Order.desc(property));
		}
		
		System.out.println(">>> " + sort);
		
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, totalFiltro(filtro));
	}

	@SuppressWarnings("deprecation")
	private Long totalFiltro(CervejaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(CervejaFilter filtro, Criteria criteria) {
		if(filtro!=null) {
			if(!StringUtils.isEmpty(filtro.getSku())) {
				criteria.add(Restrictions.eq("sku", filtro.getSku()));
			}
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			if(isEstiloFilter(filtro)) {
				criteria.add(Restrictions.eq("estilo", filtro.getEstilo()));
			}
			if(filtro.getSabor()!=null) {
				criteria.add(Restrictions.eq("sabor", filtro.getSabor()));
			}
			if(filtro.getOrigem()!=null) {
				criteria.add(Restrictions.eq("origem", filtro.getOrigem()));
			}
			if(filtro.getValorDe()!=null) {
				criteria.add(Restrictions.ge("valor", filtro.getValorDe()));
			}
			if(filtro.getValorAte()!=null) {
				criteria.add(Restrictions.le("valor", filtro.getValorAte()));
			}
		}
	}
	
	private boolean isEstiloFilter(CervejaFilter filtro) {
		return filtro.getEstilo() != null && filtro.getEstilo().getCodigo() != null;
	}

}
