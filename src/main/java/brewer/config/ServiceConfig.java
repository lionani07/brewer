package brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brewer.storage.FotoStorage;
import brewer.storage.local.FotoStorageLocal;

@Configuration
public class ServiceConfig {

	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}	
	

}
