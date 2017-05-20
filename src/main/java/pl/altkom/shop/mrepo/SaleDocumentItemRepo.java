package pl.altkom.shop.mrepo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pl.altkom.shop.model.SaleDocumentItem;

@Mapper
public interface SaleDocumentItemRepo {
	List<SaleDocumentItem> findAll();
}
