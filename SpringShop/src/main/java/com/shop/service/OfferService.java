package com.shop.service;

import com.shop.model.entity.Product;
import com.shop.pagination.EntityPage;

public interface OfferService {
//	List<LineItemDTO> getOfferForClient();
	EntityPage<Product> getPaginateOfferForClient(int page,String categoryName, int maxProductOnPage);
}
