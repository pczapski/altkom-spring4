package pl.altkom.shop.www;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.ws.rs.Path;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import pl.altkom.shop.controller.ProductSOAPWebService;

@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class CXFConfig implements ApplicationContextAware {
	@Inject
	private Bus bus;
	@Inject
	ProductSOAPWebService productSOAPWebService;
	private ApplicationContext context;

	@PostConstruct
	public void start() {
		bus.setFeatures(Arrays.asList(new LoggingFeature()));

		String[] beanNamesForAnnotation = context.getBeanNamesForAnnotation(WebService.class);
		for (String string : beanNamesForAnnotation) {
			Object bean = context.getBean(string);
			EndpointImpl endpoint = new EndpointImpl(bus, bean);
			endpoint.publish("/" + bean.getClass().getSimpleName());
		}
	}

	@Bean
	public Server rsServer() {
		String[] beanNamesForAnnotation = context.getBeanNamesForAnnotation(Path.class);
		JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
		endpoint.setBus(bus);
		endpoint.setAddress("/rest");
		endpoint.setProvider(new JacksonJsonProvider());
		List<Object> webServices = new ArrayList<>();
		for (String string : beanNamesForAnnotation) {
			webServices.add(context.getBean(string));
		}
		endpoint.setServiceBeans(webServices);
		return endpoint.create();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;

	}

}
