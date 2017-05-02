package pl.altkom.shop.client;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;

import pl.altkom.shop.client.gen.Product;
import pl.altkom.shop.client.gen.ProductSOAPWebService;
import pl.altkom.shop.client.gen.ProductSOAPWebServiceService;

public class WS {

	public static void main(String[] args) {

		ProductSOAPWebServiceService productSOAPWebServiceService = new ProductSOAPWebServiceService();
		ProductSOAPWebService productSOAPWebServicePort = productSOAPWebServiceService.getProductSOAPWebServicePort();

		// Turn off chunking so that NTLM can occur
		Client client = ClientProxy.getClient(productSOAPWebServicePort);
		HTTPConduit http = (HTTPConduit) client.getConduit();
		AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
		authorizationPolicy.setUserName("rest");
		authorizationPolicy.setPassword("rest");
		http.setAuthorization(authorizationPolicy);
		// HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		// httpClientPolicy.setConnectionTimeout(36000);
		// httpClientPolicy.setAllowChunking(false);
		// http.setClient(httpClientPolicy);

		Product findById = productSOAPWebServicePort.findById(13L);
		System.out.println(findById);
	}

}
