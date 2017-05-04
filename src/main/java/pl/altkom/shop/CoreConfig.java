package pl.altkom.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import pl.altkom.shop.service.ProductService;

@ComponentScan("pl.altkom.shop")
@EnableAspectJAutoProxy
@EnableAsync
public class CoreConfig {
	@Bean
	public ProductService productService() {
		return new ProductService();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
