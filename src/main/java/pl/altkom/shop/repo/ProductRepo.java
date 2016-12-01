package pl.altkom.shop.repo;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import pl.altkom.shop.aop.Monitoring;
import pl.altkom.shop.model.Product;

public interface ProductRepo {
	@Secured("ROLE_ADMIN")
	public Long insert(Product product);

	Long count();

	public void delete(Long id);

	public Product find(Long id);

	public void update(Product product);

	@Monitoring
	public List<Product> getAll();
}
