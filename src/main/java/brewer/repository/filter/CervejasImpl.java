package brewer.repository.filter;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
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
	public List<Cerveja> filtrar(CervejaFilter filtro) {		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		if(filtro!=null) {
			if(!StringUtils.isEmpty(filtro.getSku())) {
				criteria.add(Restrictions.eq("sku", filtro.getSku()));
			}
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getSku(), MatchMode.ANYWHERE));
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
		return criteria.list();
	}
	
	private boolean isEstiloFilter(CervejaFilter filtro) {
		return filtro.getEstilo() != null && filtro.getEstilo().getCodigo() != null;
	}

}
