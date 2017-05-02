package pl.altkom.shop.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.altkom.shop.model.Product;
import pl.altkom.shop.model.SaleDocument;
import pl.altkom.shop.model.SaleDocumentItem;

@Service
@Transactional
public class SaleDocumentService {
	@PersistenceContext
	EntityManager em;

	public Long insert(DocumentRequest documentRequest) {
		SaleDocument saleDocument = new SaleDocument();
		em.persist(saleDocument);
		List<Item> items = documentRequest.getItems();
		for (Item item : items) {
			SaleDocumentItem saleDocumentItem = new SaleDocumentItem();
			Product product = em.find(Product.class, item.getId());
			saleDocumentItem.setProduct(product);
			saleDocumentItem.setQuantity(item.getQuantity());
			saleDocumentItem.setSaleDocument(saleDocument);
			product.setQuantity(product.getQuantity() - item.getQuantity());
			saleDocument.setTotalPrice(saleDocument.getTotalPrice()
					.add(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))));
			em.persist(saleDocumentItem);
		}
		return saleDocument.getId();
	}

}
