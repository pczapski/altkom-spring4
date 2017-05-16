package pl.altkom.shop.service.event;

import java.io.Serializable;

public class ProductChangeEvent implements Serializable {

	private Long id;
	private Integer quantity;
	private Integer newQuantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getNewQuantity() {
		return newQuantity;
	}

	public void setNewQuantity(Integer newQuantity) {
		this.newQuantity = newQuantity;
	}

	public ProductChangeEvent() {
	}

	public ProductChangeEvent(Long id, Integer quantity, Integer newQuantity) {
		this.id = id;
		this.quantity = quantity;
		this.newQuantity = newQuantity;
	}
}
