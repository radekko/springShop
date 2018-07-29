package com.shop.dao;

import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.Order;

@Repository
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements OrderDao {
}
