package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.model.LineItem;
import com.shop.model.Product;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {

	@Autowired
    private ProductService productService;
	
	@Override
	public List<LineItem> getOffer() {
		List<Product> productList = productService.findAllProduct();
		List<LineItem> lineItemList = createLineItemList(productList);
		return lineItemList;
	}

	private List<LineItem> createLineItemList(List<Product> productList) {
		List<LineItem> lineItemList = new ArrayList<LineItem>();
		
		for(Product p : productList) {
			LineItem temp = new LineItem(p.getName(),p.getUniqueProductCode(),
					p.getPrice(),0);
			lineItemList.add(temp);
		}
		return lineItemList;
	}

}
