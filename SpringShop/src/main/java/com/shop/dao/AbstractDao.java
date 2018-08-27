package com.shop.dao;

import static java.lang.Math.toIntExact;

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

public class AbstractDao<PK extends Serializable, T>{
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
	
	@SuppressWarnings("unchecked")
	public T getFirstResult() {
		Criteria selectCriteria = createEntityCriteria();
		selectCriteria.setFirstResult(0);
		selectCriteria.setMaxResults(1);
		return (T) selectCriteria.list().get(0);
	}
	
	public int countTotalRecords(IEntity groupEntity) {
		Criteria projectionCriteria = createEntityCriteria();
		if(groupEntity != null)
			projectionCriteria.add(Restrictions.eq(extractGroupingFieldName(groupEntity), groupEntity));
		projectionCriteria.setProjection(Projections.rowCount());
		Long l = (Long) projectionCriteria.uniqueResult();
		return toIntExact(l);
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
	
	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}
	
	protected String extractGroupingFieldName(IEntity groupEntity) {
		String fieldName = groupEntity.getClass().getSimpleName();
		return Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
	}

	private static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
		List<T> r = new ArrayList<T>(c.size());
		for (Object o : c)
			r.add(clazz.cast(o));
		return r;
	}
	
}
