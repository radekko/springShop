package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.AbstractDao;
import com.shop.dao.ProductDao;
import com.shop.model.entity.domain.PaginationResult;
import com.shop.model.entity.persistent.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
    private ProductDao dao;
	
	@Autowired
	private PaginationService<Product> ps;
			
	@Value("${com.shop.service.ProductService.maxProductOnSite}")
	private Integer maxProductOnSite;
	
	@Value("${com.shop.service.ProductService.maxNavigationPage}")
	private Integer maxNavigationPage;

	@Override
	public void addProduct(Product product) {
		dao.addProduct(product);
	}

	@Override
	public List<Product> findAllProduct() {
		return dao.findAllProduct();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginationResult<Product> getPaginateProducts(int page) {
		return ps.getPaginationResult(page,maxProductOnSite,maxNavigationPage, (AbstractDao<?, Product>) dao);
	}

}
