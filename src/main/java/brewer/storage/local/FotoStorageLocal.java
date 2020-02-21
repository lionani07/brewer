package brewer.storage.local;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import brewer.storage.FotoStorage;

public class FotoStorageLocal implements FotoStorage {
	
	public static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	private Path local;
	private Path localTemporario;
	
	public FotoStorageLocal() {
		this(Paths.get(System.getenv("HOMEDRIVE"), "brewerFotos"));
	}
	
	public FotoStorageLocal(Path local) {
		this.local = local;
		criarPastas();
	}
	

	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = this.local.resolve("temp");
			Files.createDirectories(this.localTemporario);
			
			if(logger.isDebugEnabled()) {
				logger.debug("Pastas cridas com succeso");
				logger.debug("Local: " + this.local.toAbsolutePath());
				logger.debug("Local temp: " + this.localTemporario.toAbsolutePath());
			}
			
		} catch (IOException e) {
			throw new RuntimeException("Error ao criar pastas para salvar fotos.", e);
		}		
	}

	@Override
	public void salvarFotoTemporariamente(MultipartFile[] files) {		
		System.out.println("Salvando foto temporariamente...");		
	}

	public Path getLocal() {
		return local;
	}

	public void setLocal(Path local) {
		this.local = local;
	}

	public Path getLocalTemporario() {
		return localTemporario;
	}

	public void setLocalTemporario(Path localTemporario) {
		this.localTemporario = localTemporario;
	}
	
	

}
