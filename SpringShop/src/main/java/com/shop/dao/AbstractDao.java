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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.model.entity.persistent.IEntity;

import static java.lang.Math.toIntExact;

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
	
	public int countTotalRecords(String groupColumn,IEntity groupEntity) {
		Criteria projectionCriteria = createEntityCriteria();
		projectionCriteria.add(Restrictions.eq(groupColumn, groupEntity));
		projectionCriteria.setProjection(Projections.rowCount());
		Long l = (Long) projectionCriteria.uniqueResult();
		return toIntExact(l);
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> selectEntityToCurrentPage(int from, int to,String groupColumn,IEntity groupEntity) {
		Criteria selectCriteria = createEntityCriteria();
		
		if(groupColumn != null && groupEntity != null)
			selectCriteria.add(Restrictions.eq(groupColumn, groupEntity));
		
		selectCriteria.setFirstResult(from);
		selectCriteria.setMaxResults(to);
		return selectCriteria.list();
	}
	
	public <E> List<E> selectFirstResult() {
		return selectEntityToCurrentPage(0,1,null,null);
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
