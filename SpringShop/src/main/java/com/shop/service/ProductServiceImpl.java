package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.ProductDao;
import com.shop.model.entity.persistent.Category;
import com.shop.model.entity.persistent.Product;
import com.shop.pagination.NavigationPagesInfoCreator;
import com.shop.pagination.Page;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    private ProductDao productDao;
			
	@Value("${com.shop.service.ProductService.maxProductOnSite}")
	private Integer maxProductOnPage;
	
	@Value("${com.shop.service.ProductService.maxNavigationPage}")
	private Integer maxNavigationPage;
	
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
	public Page getPaginateProducts(int page,Category category) {
		List<Product> itemsOnPage = productDao.getProductsOnPage(page,maxProductOnPage,category);
		List<Integer> navigationPages = NavigationPagesInfoCreator
				.createNavigationPages(page, maxNavigationPage, productDao.countTotalRecordsForGroup(category), maxProductOnPage);
		
		return new Page(itemsOnPage,navigationPages);
	}

}
