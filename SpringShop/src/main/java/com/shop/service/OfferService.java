package com.shop.service;

import java.util.List;

import com.shop.dao.PaginationResult;
import com.shop.model.entity.domain.LineItem;
import com.shop.model.entity.persistent.Product;

public interface OfferService {
	List<LineItem> getOfferForClient();


	PaginationResult<LineItem> getPaginationOfferForClient(int page); 
}
