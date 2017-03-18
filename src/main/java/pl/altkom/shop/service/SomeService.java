package pl.altkom.shop.service;

import java.math.BigDecimal;
import java.sql.Connection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import pl.altkom.shop.model.Product;
import pl.altkom.shop.repo.ProductRepo;

@Service
@Transactional
public class SomeService {
	@Inject
	ProductRepo repo;
	@PersistenceContext
	EntityManager em;
	@Inject
	DataSource ds;

	public void doito(Product product) {
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

			@Override
			public void suspend() {
				// TODO Auto-generated method stub

			}

			@Override
			public void resume() {
				// TODO Auto-generated method stub

			}

			@Override
			public void flush() {
				System.out.println("flush");

			}

			@Override
			public void beforeCompletion() {
				System.out.println("beforeCompletion");

			}

			@Override
			public void beforeCommit(boolean readOnly) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterCompletion(int status) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterCommit() {
				// TODO Auto-generated method stub

			}
		});
		Connection connection = DataSourceUtils.getConnection(ds);
		em.persist(new Product("a", "b", 10, BigDecimal.TEN));
		if (product.getId() != null) {
			em.merge(product);
		} else {
			em.persist(product);
		}
	}
}
