package brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	
	void salvarFotoTemporariamente(MultipartFile[] files);

}
