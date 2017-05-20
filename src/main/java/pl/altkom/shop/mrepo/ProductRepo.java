package pl.altkom.shop.mrepo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pl.altkom.shop.model.Product;

@Mapper
public interface ProductRepo {
	List<Product> findAll();

	Product findById(Long id);

	void save(Product p);
}
