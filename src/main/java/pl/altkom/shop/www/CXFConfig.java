package pl.altkom.shop.www;

import javax.inject.Inject;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import pl.altkom.shop.controller.ProductSOAPWebService;

@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class CXFConfig {
	@Autowired
	private Bus bus;
	@Inject
	ProductSOAPWebService saleDocumentSOAPWebService;

	@Bean
	public Endpoint myServiceEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, saleDocumentSOAPWebService);
		endpoint.publish("/sale");
		return endpoint;
	}

}
