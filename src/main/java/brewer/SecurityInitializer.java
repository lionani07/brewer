package brewer;

import java.util.EnumSet;

import javax.servlet.ServletContext;
import javax.servlet.SessionTrackingMode;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer{
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		super.beforeSpringSecurityFilterChain(servletContext);
		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
	}

}
