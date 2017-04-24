package pl.altkom.shop.www;

import javax.inject.Inject;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import pl.altkom.shop.controller.ProductSOAPWebService;

@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class CXFConfig {
	@Inject
	private Bus bus;
	@Inject
	ProductSOAPWebService productSOAPWebService;

	@Bean
	public Endpoint productSOAP() {
		EndpointImpl endpoint = new EndpointImpl(bus, productSOAPWebService);
		endpoint.publish("/product");
		return endpoint;
	}

}
