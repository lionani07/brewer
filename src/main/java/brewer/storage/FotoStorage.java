package brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	
	String salvarFotoTemporariamente(MultipartFile[] files);	
	void salvarFoto(String foto);
	
	byte[] recuperarFotoTemporaria(String nome);
	byte[] recuperarFoto(String nome);

	

}
