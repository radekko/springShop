package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.ProductDao;
import com.shop.model.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
    private ProductDao dao;
	
	@Override
	public void addProduct(Product product) {
		dao.addProduct(product);
	}

	@Override
	public List<Product> findAllProduct() {
		return dao.findAllProduct();
	}

}
