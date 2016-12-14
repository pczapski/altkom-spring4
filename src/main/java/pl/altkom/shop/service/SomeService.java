package pl.altkom.shop.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.altkom.shop.model.Product;
import pl.altkom.shop.repo.ProductRepo;

@Service
@Transactional
public class SomeService {
	@Inject
	ProductRepo repo;
	@PersistenceContext
	EntityManager em;

	public void doito(Product product) {
		if (product.getId() != null) {
			em.merge(product);
		} else {
			em.persist(product);
		}
	}
}
