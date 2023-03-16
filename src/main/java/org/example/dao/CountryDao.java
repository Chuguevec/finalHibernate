package org.example.dao;

import org.example.domain.Country;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CountryDao extends GenericDao<Country>{
    public CountryDao(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }

    @Override
    public List<Country> findAll() {
        Query<Country> query = getCurrentSession().createQuery("select c from Country c join fetch c.languages" , Country.class);
        return query.list();
    }
}
