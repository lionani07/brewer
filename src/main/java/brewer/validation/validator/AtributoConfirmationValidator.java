package brewer.validation.validator;

import java.util.Objects;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import brewer.validation.AtributoConfirmacao;

public class AtributoConfirmationValidator implements ConstraintValidator<AtributoConfirmacao, Object>  {
	
	private AtributoConfirmacao constraintAnnotation;
	private Object valorAtributo;
	private Object atributoConfirmacao;
	
	@Override		
	public void initialize(AtributoConfirmacao constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;		
	}		

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {		
		setValuesAttributes(object);
		boolean valido = isAmbosNull() || isAttributeEquals();
		
		if (!valido) {
			addConstraintViolation(context);
		}
		return valido;
	}
	
	private void setValuesAttributes(Object object) {
		try {
			this.valorAtributo = BeanUtils.getProperty(object, constraintAnnotation.atributo());
			this.atributoConfirmacao = BeanUtils.getProperty(object, constraintAnnotation.atributoConfirmacao());			
		} catch (Exception e) {
			throw new RuntimeException("Error recuperando os atributos", e);
		}		
	}
	
	private boolean isAttributeEquals() {
		
		return Optional
				.ofNullable(valorAtributo)
				.map(valorAtributo -> valorAtributo.equals(atributoConfirmacao))
				.orElse(false);				
		
	}
	
	private boolean isAmbosNull() {
		return Objects.isNull(valorAtributo) && Objects.isNull(atributoConfirmacao);
	}
	
	private void addConstraintViolation(ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		String msg = context.getDefaultConstraintMessageTemplate();
		ConstraintViolationBuilder builder = context.buildConstraintViolationWithTemplate(msg);
		builder.addPropertyNode(constraintAnnotation.atributoConfirmacao()).addConstraintViolation();
	}

	

}
