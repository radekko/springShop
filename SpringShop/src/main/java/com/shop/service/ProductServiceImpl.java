package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.AbstractDao;
import com.shop.dao.CategoryDao;
import com.shop.dao.ProductDao;
import com.shop.model.entity.domain.PaginationResult;
import com.shop.model.entity.persistent.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
    private ProductDao proDao;
	
	@Autowired
	private CategoryDao catDao;
	
	@Autowired
	private PaginationServiceImpl<Product> ps;
			
	@Value("${com.shop.service.ProductService.maxProductOnSite}")
	private Integer maxProductOnSite;
	
	@Value("${com.shop.service.ProductService.maxNavigationPage}")
	private Integer maxNavigationPage;
	
	private String groupingColumnName="category";
	
	@Override
	public void addProduct(Product product) {
		proDao.addProduct(product);
	}

	@Override
	public List<Product> findAllProduct() {
		return proDao.findAllProduct();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginationResult<Product> getPaginateProducts(int page,String categoryName) {
		return ps.getPaginationResult(
				page,maxProductOnSite,maxNavigationPage, 
				(AbstractDao<?, Product>) proDao,groupingColumnName,catDao.getCategoryByName(categoryName));
	}

}
