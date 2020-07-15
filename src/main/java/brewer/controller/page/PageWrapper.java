package brewer.controller.page;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {
	
	private Page<T> page;
	private UriComponentsBuilder uriBuilder;
	
	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		String httpUrl = httpServletRequest.getRequestURL().append(
				httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
				.toString().replaceAll("\\+", "%20");
		this.uriBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);
	}
	
	public List<T> getConteudo() {
		return this.page.getContent();
	}
	
	public boolean isVazia() {
		return this.page.getContent().isEmpty();
	}
	
	public int getAtual() {
		return this.page.getNumber();
	}
	
	public boolean isPrimeira() {
		return this.page.isFirst();
	}
	
	public boolean isUltima() {
		return this.page.isLast();
	}
	
	public int getTotal() {
		return this.page.getTotalPages();
	}
	
	public String urlParaPagina(int pagina) {
		return this.uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}
	
	public String urlOrdenada(String propiedade) {
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder
				.fromUriString(this.uriBuilder.build(true).encode().toUriString());
		
		String valorSort = String.format("%s,%s", propiedade, inverterDirecaoOrder(propiedade));
		
		return uriBuilderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();
	}
	
	public String inverterDirecaoOrder(String propiedade) {
		String direcaoOrder = "asc";
		
		Order order = Objects.isNull(page.getSort()) ? null : page.getSort().getOrderFor(propiedade);
		
		if (!Objects.isNull(order)) {
			direcaoOrder = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		
		return direcaoOrder;		
	}
	
	public boolean isDescendente(String propiedade) {
		return inverterDirecaoOrder(propiedade).equals("asc");		
	}
	
	public boolean isOrdenada(String propiedade) {
		Order order = Objects.isNull(page.getSort()) ? null : page.getSort().getOrderFor(propiedade);
		return Objects.isNull(order) ? false : true;
	}
	

}
