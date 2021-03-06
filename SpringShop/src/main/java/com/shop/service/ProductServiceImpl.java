package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.ProductDao;
import com.shop.model.entity.Category;
import com.shop.model.entity.Product;
import com.shop.pagination.EntityPage;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    private ProductDao productDao;
	
	@Autowired
	public ProductServiceImpl(ProductDao proDao) {
		this.productDao = proDao;
	}

	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

	@Override
	public List<Product> findAllProduct() {
		return productDao.findAllProduct();
	}

	@Override
	public EntityPage<Product> getPaginateProducts(int page,Category category,int maxProductOnPage) {
		return productDao.getPageInCategory(page,maxProductOnPage,category);
	}

}
