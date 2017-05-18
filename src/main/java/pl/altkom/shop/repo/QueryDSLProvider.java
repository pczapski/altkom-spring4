package pl.altkom.shop.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mysema.query.support.Expressions;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.ComparablePath;
import com.mysema.query.types.path.SimplePath;

public class QueryDSLProvider {
	Map<String, SimplePath> currentPaths = new HashMap<>();
	private SimplePath main;

	public QueryDSLProvider(SimplePath... paths) {
		main = paths[0];
		for (SimplePath simplePath : paths) {
			String name = simplePath.getMetadata().getName();
			currentPaths.put(name, simplePath);
		}
	}

	public Predicate[] getPredicates(Map<String, Object> where) {
		List<Predicate> predicates = new ArrayList<>();
		Set<String> keySet = where.keySet();
		for (String path : keySet) {
			Object val = where.get(path);
			if (path.contains(".")) {
				String[] split = path.split("\\.");
				SimplePath simplePath = currentPaths.get(split[0]);
				BooleanExpression createExpression = createExpression(simplePath, split[1], val);
				predicates.add(createExpression);
			} else {
				BooleanExpression createExpression = createExpression(main, path, val);
				predicates.add(createExpression);
			}
		}
		return predicates.toArray(new Predicate[0]);
	}

	public OrderSpecifier<?>[] getOrders(Map<String, Integer> order) {
		List<OrderSpecifier> predicates = new ArrayList<>();
		Set<String> keySet = order.keySet();
		for (String path : keySet) {
			Integer val = order.get(path);
			if (path.contains(".")) {
				String[] split = path.split("\\.");
				SimplePath simplePath = currentPaths.get(split[0]);
				ComparablePath<Comparable> comparablePath = Expressions.comparablePath(Comparable.class, simplePath,
						split[1]);
				predicates.add(val == 1 ? comparablePath.asc() : comparablePath.desc());
			} else {
				ComparablePath<Comparable> comparablePath = Expressions.comparablePath(Comparable.class, main, path);
				predicates.add(val == 1 ? comparablePath.asc() : comparablePath.desc());
			}
		}
		return predicates.toArray(new OrderSpecifier[0]);
	}

	private BooleanExpression createExpression(SimplePath path, String pathString, Object val) {
		if (val instanceof String) {
			return Expressions.stringPath(pathString).startsWithIgnoreCase(val.toString());
		}
		return Expressions.path(Object.class, path, pathString).eq(val);
	}
}
