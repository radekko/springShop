package com.shop.dao;

import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.Order;
import com.shop.pagination.EntityPage;

@Repository
public class OrderDaoImpl extends AbstractDaoWithPagination<Integer, Order> implements OrderDao {

	@Override
	public EntityPage<Order> getOrdersOnPage(int page, int maxProductOnPage) {
		return createEntityPageIncludingFetch(page,maxProductOnPage,"setOfLineItems");
	}
//	using jpql
//	@SuppressWarnings("unchecked")
//	private List<Order> selectItems(int from, int to){
//		Query extractIdsQuery = getSession().createQuery("Select o.id from Order o order by o.id");
//		List<Integer> listIds = extractIdsQuery.list();
//		Query query = getSession().createQuery("select o from Order o left join fetch o.setOfLineItems where o.id in :ids");
//		to = (to>listIds.size()) ? listIds.size() : to;
//		query.setParameterList("ids", listIds.subList(from,to));
//				
//		return query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//	}
}
