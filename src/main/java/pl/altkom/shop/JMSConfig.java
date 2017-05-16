package pl.altkom.shop;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
public class JMSConfig {
	public static final String TOPIC_LISTNER = "topicListner";
	public static final String TOPIC_LISTNER2 = "topicListner2";
	public static final String TOPIC_TEMPLATE = "topic";

	public static final String ORDER_QUEUE = "order-queue";
	public static final String PRODUCT_QUANTITY_CHANGE = "product-quantity-change";

	String BROKER_URL = "tcp://localhost:61616";
	String BROKER_USERNAME = "admin";
	String BROKER_PASSWORD = "admin";

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setTrustAllPackages(true);
		connectionFactory.setBrokerURL(BROKER_URL);
		connectionFactory.setPassword(BROKER_USERNAME);
		connectionFactory.setUserName(BROKER_PASSWORD);
		return connectionFactory;
	}

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Bean
	@Primary
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(ORDER_QUEUE);
		template.setMessageConverter(jacksonJmsMessageConverter());
		template.setSessionAcknowledgeMode(javax.jms.Session.CLIENT_ACKNOWLEDGE);
		template.setExplicitQosEnabled(true);
		template.setSessionTransacted(true);
		return template;
	}

	@Bean(name = TOPIC_TEMPLATE)
	public JmsTemplate topicJmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(PRODUCT_QUANTITY_CHANGE);
		template.setMessageConverter(jacksonJmsMessageConverter());
		template.setSessionAcknowledgeMode(javax.jms.Session.CLIENT_ACKNOWLEDGE);
		template.setExplicitQosEnabled(true);
		template.setSessionTransacted(true);
		template.setPubSubDomain(true);
		return template;
	}

	@Bean
	@Primary
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setConcurrency("1-3");
		factory.setSessionTransacted(true);
		factory.setMessageConverter(jacksonJmsMessageConverter());
		return factory;
	}

	@Bean(name = TOPIC_LISTNER)
	public DefaultJmsListenerContainerFactory topicListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setSessionTransacted(true);
		factory.setMessageConverter(jacksonJmsMessageConverter());
		factory.setSubscriptionDurable(true);
		factory.setPubSubDomain(true);
		factory.setClientId("spring-shop");
		return factory;
	}

	@Bean(name = TOPIC_LISTNER2)
	public DefaultJmsListenerContainerFactory topicListenerContainerFactory2() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setSessionTransacted(true);
		factory.setMessageConverter(jacksonJmsMessageConverter());
		factory.setSubscriptionDurable(true);
		factory.setPubSubDomain(true);
		factory.setClientId("spring-shop2");
		return factory;
	}
}
