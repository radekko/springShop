package com.shop.service;

import java.util.List;

import com.shop.model.entity.domain.LineItem;
import com.shop.pagination.DTOPageWithNavigation;

public interface OfferService {
	List<LineItem> getOfferForClient();
	DTOPageWithNavigation<LineItem> getPaginateOfferForClient(int page,String categoryName);
}
