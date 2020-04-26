package brewer.repository.filter;

import java.io.Serializable;

public class EstiloFilter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	public EstiloFilter() {		
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

}
