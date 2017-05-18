package pl.altkom.shop.repo;

import com.mysema.query.annotations.QueryProjection;

public class SaleDocumentInfo {

	private Long docNumber;
	private Long idSaleDocItem;
	private String productName;

	@QueryProjection
	public SaleDocumentInfo(Long docNumber, Long idSaleDocItem, String productName) {
		this.docNumber = docNumber;
		this.idSaleDocItem = idSaleDocItem;
		this.productName = productName;

	}

	public Long getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(Long docNumber) {
		this.docNumber = docNumber;
	}

	public Long getIdSaleDocItem() {
		return idSaleDocItem;
	}

	public void setIdSaleDocItem(Long idSaleDocItem) {
		this.idSaleDocItem = idSaleDocItem;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
