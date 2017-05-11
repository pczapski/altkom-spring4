package pl.altkom.shop.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

	private long id;
	private int quantity;

	@JsonCreator
	public Item(@JsonProperty("id") long id, @JsonProperty("quantity") int quantity) {
		this.setId(id);
		this.setQuantity(quantity);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
