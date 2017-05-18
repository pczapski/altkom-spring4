package pl.altkom.shop.repo;

import java.util.List;

import pl.altkom.shop.model.SaleDocument;

public interface SaleDocumentRepoCustom {

	SaleDocument strange(Long id);

	List<SaleDocumentInfo> findSaleDocument(SaleDocumentSearcher search);
}
