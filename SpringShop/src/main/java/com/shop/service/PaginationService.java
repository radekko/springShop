package com.shop.service;

import com.shop.dao.AbstractDao;
import com.shop.model.entity.domain.PaginationResult;

public interface PaginationService<E> {
	PaginationResult<E> getPaginationResult(int page, int maxResult, int maxNavigationPage, AbstractDao<?, E> ab);
}