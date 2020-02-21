package brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import brewer.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<FotoDTO> resultado;
	private FotoStorage fotoStorage;
	
	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado, FotoStorage fotoStorage) {		
		this.files = files;
		this.resultado = resultado;
		this.fotoStorage = fotoStorage;
	}

	@Override
	public void run() {	
		
		this.fotoStorage.salvarFotoTemporariamente(files);
		
		String nome = this.files[0].getOriginalFilename();
		String contentType = this.files[0].getContentType();		
		resultado.setResult(new FotoDTO(nome, contentType));
	}

}
