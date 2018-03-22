package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.PaginationResult;
import com.shop.model.entity.domain.LineItem;
import com.shop.model.entity.persistent.Product;

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
		PaginationResult<Product> productList = productService.paginateProducts(page);
		List<LineItem> lineItemList= createLineItemList(productList.getList());
		PaginationResult<LineItem> lineItemPaginationList = new PaginationResult<LineItem>(productList,lineItemList);
		return lineItemPaginationList;
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
