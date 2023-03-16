package org.example.dao;

import org.example.domain.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CityDao extends GenericDao<City>{

    public CityDao(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    @Override
    public City getById(int id) {
        Query<City> query = getCurrentSession().createQuery("select c from City c join fetch c.country where c.id = :ID", City.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }
}
