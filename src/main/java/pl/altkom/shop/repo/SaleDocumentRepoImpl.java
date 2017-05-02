package pl.altkom.shop.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import pl.altkom.shop.model.SaleDocument;

@Repository
public class SaleDocumentRepoImpl implements SaleDocumentRepoCustom {
	@PersistenceContext
	EntityManager em;

	@Override
	public SaleDocument strange(Long id) {
		return em.find(SaleDocument.class, 13L);
	}

}
