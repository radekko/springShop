package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.model.entity.domain.LineItem;
import com.shop.model.entity.domain.PaginationResult;
import com.shop.model.entity.persistent.Product;
/* Service to convert products to line items */
@Service
@Transactional
public class OfferServiceImpl implements OfferService {

	@Autowired
    private ProductService productService;

	@Override
	public List<LineItem> getOfferForClient() {
		List<Product> productList = productService.findAllProduct();
		List<LineItem> lineItemList = createLineItemList(productList);
		return lineItemList;
	}
	
	@Override
	public PaginationResult<LineItem> getPaginationOfferForClient(int page) {
		PaginationResult<Product> paginationProducts = productService.getPaginateProducts(page);
		List<Product> productList = paginationProducts.getEntitiesOnChosenPage();
		List<LineItem> lineItemList =createLineItemList(productList);
		PaginationResult<LineItem> lineItemPaginationList = new PaginationResult<LineItem>(paginationProducts,lineItemList);
		return lineItemPaginationList;
	}

	private List<LineItem> createLineItemList(List<Product> list) {
		List<LineItem> lineItemList = new ArrayList<LineItem>();
		
		for(Product p : list) {
			LineItem temp = new LineItem(p.getName(),p.getUniqueProductCode(),
					p.getPrice(),0);
			lineItemList.add(temp);
		}
		return lineItemList;
	}

}
