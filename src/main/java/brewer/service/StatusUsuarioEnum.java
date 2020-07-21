package brewer.service;

import brewer.repository.Usuarios;

public enum StatusUsuarioEnum {
	
	ATIVAR {
		@Override
		public void executar(Long[] codigos, Usuarios usuarios) {
			usuarios.findByCodigoIn(codigos).forEach(usuario -> usuario.setAtivo(true));			
		}
	},
	DESATIVAR {
		@Override
		public void executar(Long[] codigos, Usuarios usuarios) {
			usuarios.findByCodigoIn(codigos).forEach(usuario -> usuario.setAtivo(false));
		}
	};
	
	public abstract void executar(Long[] codigos, Usuarios usuarios);

}
