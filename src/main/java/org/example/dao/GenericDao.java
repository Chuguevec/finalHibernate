package org.example.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class GenericDao<T> {
    private final Class<T> clazz;
    private SessionFactory sessionFactory;

    public GenericDao(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    public T getById(final int id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int offset, int count) {
        Query<T> query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<T> findAll() {
        Query<T> query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        return query.list();
    }

    public T save(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        getCurrentSession().merge(entity);
        return entity;
    }

    public int getTotalCount (){
        Query<Long> query = getCurrentSession()
                .createQuery("SELECT COUNT(*) FROM " +clazz.getName(), Long.class);
        return Math.toIntExact(query.getSingleResult());
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(final int entityId) {
        final T entity = getById(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
