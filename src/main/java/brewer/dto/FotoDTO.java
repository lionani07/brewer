package brewer.dto;

public class FotoDTO {
	
	private String foto;
	private String contentType;
	
	public FotoDTO(String foto, String contentType) {
		this.foto = foto;
		this.contentType = contentType;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
}
