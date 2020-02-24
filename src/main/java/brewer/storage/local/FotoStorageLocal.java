package brewer.storage.local;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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
	public String salvarFotoTemporariamente(MultipartFile[] files) {
		MultipartFile arquivo = files[0];
		String nomeArquivo = null;
		if(!arquivo.isEmpty()) {
			try {
				nomeArquivo = renomarArquivo(arquivo.getOriginalFilename());
				arquivo.transferTo(this.localTemporario.resolve(nomeArquivo));
			}  catch (IOException e) {				
				throw new RuntimeException("Error ao salvar a foto temporariemnte", e);
			}
		}
		return nomeArquivo;
	}
	
	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {			
			throw new RuntimeException("Error ao recuperar foto temporaria", e);
		}
	}
	
	private String renomarArquivo(String nomeOriginal) {
		return UUID.randomUUID() + "_" + nomeOriginal;
	}

	


}
