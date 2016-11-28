package pl.altkom.shop;

import org.springframework.context.annotation.Bean;

import pl.altkom.shop.service.ProductService;

public class CoreConfig {
	@Bean
	public ProductService productService() {
		return new ProductService();
	}
}
