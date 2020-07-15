package brewer.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

@Configuration
@EnableCaching
public class CacheConfig {
	
	@Bean
	public CacheManager cacheManager() {
		
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder
				.newBuilder()
				.maximumSize(3)
				.expireAfterAccess(20,
				TimeUnit.SECONDS);

		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(cacheBuilder);
		return cacheManager;

	}

}
