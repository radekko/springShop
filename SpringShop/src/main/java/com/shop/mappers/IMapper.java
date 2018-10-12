package com.shop.mappers;

import com.shop.model.entity.domain.IDTO;
import com.shop.model.entity.persistent.IEntity;

public interface IMapper<E extends IEntity,E2 extends IDTO> {
	E2 convertEntityToDTO(E e);
	E convertDTOToEntity(E2 e2);
}
