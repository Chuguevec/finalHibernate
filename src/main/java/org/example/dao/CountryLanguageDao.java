package org.example.dao;

import org.example.domain.CountryLanguage;
import org.hibernate.SessionFactory;

public class CountryLanguageDao extends GenericDao<CountryLanguage> {
    public CountryLanguageDao(SessionFactory sessionFactory) {
        super(CountryLanguage.class, sessionFactory);
    }
}
