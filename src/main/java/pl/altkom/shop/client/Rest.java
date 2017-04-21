package pl.altkom.shop.client;

import java.util.Collections;

import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import pl.altkom.shop.model.Product;

public class Rest {

	public static void main(String[] args) {
		WebClient client = WebClient.create("http://localhost:8080/spring-shop/api",
				Collections.singletonList(new JacksonJsonProvider()), "rest", "rest", null);
		Product product = client.path("/products/4").get(Product.class);
		System.out.println(product);

	}

}
