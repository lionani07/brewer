package brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	
	String salvarFotoTemporariamente(MultipartFile[] files);
	
	byte[] recuperarFotoTemporaria(String nome);

}
