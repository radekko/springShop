package com.shop.service;

import java.util.List;

import com.shop.model.LineItem;

public interface OfferService {
	List<LineItem> getOfferForClient(); 
}
