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
		return convertProductListToLineItemList(productService.findAllProduct());
	}
	
	@Override
	public PaginationResult<LineItem> getPaginationOfferForClient(int page) {
		PaginationResult<Product> paginationProducts = productService.getPaginateProducts(page);
		List<Product> productList = paginationProducts.getEntitiesOnChosenPage();
		List<LineItem> lineItemList = convertProductListToLineItemList(productList);
		return new PaginationResult<LineItem>(paginationProducts,lineItemList);
	}

	private List<LineItem> convertProductListToLineItemList(List<Product> list) {
		List<LineItem> lineItemList = new ArrayList<LineItem>();
		
		for(Product p : list) 
			lineItemList.add(
					new LineItem(p.getName(),p.getUniqueProductCode(),p.getPrice(),0)
					);

		return lineItemList;
	}

}
