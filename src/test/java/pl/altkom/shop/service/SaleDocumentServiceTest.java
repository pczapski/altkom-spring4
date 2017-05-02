package pl.altkom.shop.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pl.altkom.shop.CoreConfig;
import pl.altkom.shop.model.Product;
import pl.altkom.shop.model.SaleDocument;
import pl.altkom.shop.repo.ProductRepo;
import pl.altkom.shop.repo.SaleDocumentRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CoreConfig.class)
@Rollback
@Transactional
@ActiveProfiles("test")
@WebAppConfiguration
public class SaleDocumentServiceTest {

	@Inject
	ProductRepo repo;
	@Inject
	SaleDocumentRepo saleDocumentRepo;

	@Test
	public void shoulAddProduct() throws Exception {
		// given
		Product product = new Product("rower", "2", 11, BigDecimal.TEN);
		int beforeInsertSize = repo.getAll().size();

		// when
		repo.insert(product);

		// then
		assertThat(repo.getAll().size()).isGreaterThan(beforeInsertSize);
	}

	@Test
	public void shoulFindSaleDocumentByNumber() throws Exception {
		// given

		// when
		SaleDocument findByNumber = saleDocumentRepo.findByNumber(1L);

		// then
		assertThat(findByNumber).isNotNull();
	}
}
