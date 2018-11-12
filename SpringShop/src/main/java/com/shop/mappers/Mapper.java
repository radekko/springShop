package com.shop.mappers;

import com.shop.model.dto.IDTO;
import com.shop.model.entity.IEntity;

public interface Mapper<E extends IEntity,E2 extends IDTO> {
	E2 convertEntityToDTO(E e);
}
