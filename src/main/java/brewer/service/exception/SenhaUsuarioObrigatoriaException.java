package brewer.service.exception;

public class SenhaUsuarioObrigatoriaException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public SenhaUsuarioObrigatoriaException(String msg) {
		super(msg);
	}

}
