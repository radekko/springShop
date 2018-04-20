package com.shop.service;

import java.util.List;

import com.shop.model.entity.domain.LineItem;
import com.shop.model.entity.domain.PaginationResult;

public interface OfferService {
	List<LineItem> getOfferForClient();
	PaginationResult<?> getPaginationOfferForClient(int page,String categoryName);
}
