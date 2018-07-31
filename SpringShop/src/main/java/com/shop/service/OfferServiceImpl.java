package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.model.entity.domain.LineItem;
import com.shop.model.entity.persistent.Product;
import com.shop.pagination.Page;
/* Service to convert products to line items and display as offer*/
@Service
@Transactional
public class OfferServiceImpl implements OfferService {

    private ProductService productService;
	private CategoryService categoryService;
	
	@Autowired 
	public OfferServiceImpl(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@Override
	public List<LineItem> getOfferForClient() {
		return convertProductListToLineItemList(productService.findAllProduct());
	}
	//TODO: 1.do it with lambda, 2.add type to Page?
	@SuppressWarnings("unchecked")
	@Override
	public Page getPaginationOfferForClient(int page,String categoryName) {
		Page paginationProducts = productService.getPaginateProducts(page,categoryService.getCategoryByName(categoryName));
		List<LineItem> list = preparePaginationResultOfLineItems((List<Product>) paginationProducts.getItems());
		paginationProducts.setItems(list);
		return paginationProducts;
	}

	private List<LineItem> preparePaginationResultOfLineItems(
			List<Product> paginationProducts) {

		List<LineItem> lineItemList = convertProductListToLineItemList(paginationProducts);
		return lineItemList;
	}

	private List<LineItem> convertProductListToLineItemList(List<Product> list) {
		List<LineItem> lineItemList = new ArrayList<LineItem>();
		if(list.isEmpty())
			return lineItemList;
		
		for(Product p : list) 
			lineItemList.add(
					new LineItem(p.getName(),p.getUniqueProductCode(),p.getPrice(),0)
					);

		return lineItemList;
	}
	private LineItem convertToLineItem(final Product p) {
	    final LineItem lineItem = new LineItem(p.getName(),p.getUniqueProductCode(),p.getPrice(),0);
	    return lineItem;
	}

}
