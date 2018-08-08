package com.shop.service;

import java.util.List;

import com.shop.model.entity.domain.LineItem;
import com.shop.pagination.Page;

public interface OfferService {
	List<LineItem> getOfferForClient();
	Page<LineItem> getPaginateOfferForClient(int page,String categoryName);
}
