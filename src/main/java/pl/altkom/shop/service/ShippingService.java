package pl.altkom.shop.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import pl.altkom.shop.JMSConfig;
import pl.altkom.shop.MailSenderService;
import pl.altkom.shop.service.event.OrderCreated;

@Service
public class ShippingService {
	static Logger log = LoggerFactory.getLogger(ShippingService.class);
	@Inject
	MailSenderService mailSender;

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void sendToClient(OrderCreated event) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mailSender.send("m.szajowski@gmail.com", "Nowe zamówienie", "Docukment");
		log.info(Thread.currentThread().getName() + " Wyslano 2");
	}

	@JmsListener(destination = JMSConfig.ORDER_QUEUE)
	public void handle(OrderCreated message) throws Exception {
		Long id = message.getId();
		// ActiveMQObjectMessage qma = (ActiveMQObjectMessage) message;
		// Serializable object = qma.getObject();
		log.info(Thread.currentThread().getName() + " START");
		Thread.sleep(10000);
		mailSender.send("m.szajowski@gmail.com", "Nowe zamówienie", "Docukment");
		// log.info(Thread.currentThread().getName() + " STOP");
	}

}
