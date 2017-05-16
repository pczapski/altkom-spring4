package pl.altkom.shop.service;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.altkom.shop.JMSConfig;
import pl.altkom.shop.model.Product;
import pl.altkom.shop.repo.ProductRepo;
import pl.altkom.shop.service.event.ProductChangeEvent;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepo repo;

	@Inject
	@Resource(name = JMSConfig.TOPIC_TEMPLATE)
	JmsTemplate jmsTemplate;

	public void insert(Product product) {
		repo.insert(product);

	}

	public void update(Product product) {
		Product oldProductState = repo.find(product.getId());
		Integer quantity = oldProductState.getQuantity();
		Integer newQuantity = product.getQuantity();
		repo.update(product);

		jmsTemplate.convertAndSend(new ProductChangeEvent(product.getId(), quantity, newQuantity));
	}

}
