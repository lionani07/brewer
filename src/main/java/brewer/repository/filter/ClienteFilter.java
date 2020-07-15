package brewer.repository.filter;

import java.io.Serializable;

public class ClienteFilter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String cpfOuCnpj;
	
	public ClienteFilter() {		
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}
	
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

}
