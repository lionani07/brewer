package brewer.repository.paginacao;

import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginacaoUtil {
	
	public void preparar(Criteria criteria, Pageable pageable) {
		criteria.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		criteria.setMaxResults(pageable.getPageSize());
		
		Sort sort = pageable.getSort();		
		if (!Objects.isNull(sort) && sort.iterator().hasNext()) {			
			Sort.Order order = sort.iterator().next();				
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));					
		}
	}

}
