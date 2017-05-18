package pl.altkom.shop.repo;

import java.util.List;
import java.util.Map;

import pl.altkom.shop.model.SaleDocument;

public interface SaleDocumentRepoCustom {

	SaleDocument strange(Long id);

	List<SaleDocumentInfo> findSaleDocument(SaleDocumentSearcher search);

	List<SaleDocumentInfo> findByGUI(Map<String, Object> where, Map<String, Integer> order);
}
