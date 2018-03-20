package com.shop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.Product;

@Repository
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao{

	@Override
	public void addProduct(Product product) {
	}

	@Override
	public List<Product> findAllProduct() {
		return getAll();
	}
	@Override
	public Product getByUniqueCode(String uniquecode) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("uniqueProductCode", uniquecode));
		return (Product) criteria.uniqueResult();
	}

}
