package com.shop.service;

import java.util.List;

import com.shop.dao.PaginationResult;
import com.shop.model.entity.domain.LineItem;

public interface OfferService {
	List<LineItem> getOfferForClient();
	PaginationResult<LineItem> getPaginationOfferForClient(int page); 
}
