package com.shop.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T>{
	private final Class<T> persistentClass;
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public List<T> getAll() {
		return castList(persistentClass, createEntityCriteria().list());
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void save(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void merge(T entity) {
		getSession().merge(entity);
	}
	
	public Long countTotalRecords() {
		Criteria projectionCriteria = createEntityCriteria();
		projectionCriteria.setProjection(Projections.rowCount());
		return (Long) projectionCriteria.uniqueResult();
	}

	public <E> List<E> selectEntityToCurrentPage(int from, int to) {
		Criteria selectCriteria = createEntityCriteria();
		selectCriteria.setFirstResult(from);
		selectCriteria.setMaxResults(to);
		return selectCriteria.list();
	}
	

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

	private static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
		List<T> r = new ArrayList<T>(c.size());
		for (Object o : c)
			r.add(clazz.cast(o));
		return r;
	}
}
