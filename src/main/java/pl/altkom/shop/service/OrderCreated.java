package pl.altkom.shop.service;

import java.io.Serializable;

public class OrderCreated implements Serializable {

	private Long id;

	public OrderCreated() {
	}

	public OrderCreated(Long id) {
		this.setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
