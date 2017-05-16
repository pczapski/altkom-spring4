package pl.altkom.shop;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import pl.altkom.shop.service.event.ProductChangeEvent;

@Component
public class ProductChangeEventListener {

	@JmsListener(destination = JMSConfig.PRODUCT_QUANTITY_CHANGE, containerFactory = JMSConfig.TOPIC_LISTNER)
	public void handle(ProductChangeEvent message) throws Exception {
		System.out.println("GET " + message);
		System.out.println(message.getId() + " " + message.getQuantity() + " -> " + message.getNewQuantity());
	}

	@JmsListener(destination = JMSConfig.PRODUCT_QUANTITY_CHANGE, containerFactory = JMSConfig.TOPIC_LISTNER2)
	public void handle2(ProductChangeEvent message) throws Exception {
		System.out.println("GET2 " + message);
		System.out.println(message.getId() + " " + message.getQuantity() + " -> " + message.getNewQuantity());
	}
}
