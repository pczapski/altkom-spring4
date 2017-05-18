package pl.altkom.shop.repo;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

public class SaleDocumentSearcher {
	public Predicate where;
	public OrderSpecifier<?> orderBy;
	public Integer page;
	public Integer pageSize;

}
