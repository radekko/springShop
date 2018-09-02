package com.shop.service;

import java.util.List;

import com.shop.model.entity.domain.LineItemDTO;
import com.shop.pagination.DTOPageWithNavigation;

public interface OfferService {
	List<LineItemDTO> getOfferForClient();
	DTOPageWithNavigation<LineItemDTO> getPaginateOfferForClient(int page,String categoryName);
}
