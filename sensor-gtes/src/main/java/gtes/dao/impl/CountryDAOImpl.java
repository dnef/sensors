package gtes.dao.impl;

import gtes.dao.CountryDAO;
import gtes.dao.common.AbstractHibernateDAO;
import gtes.entity.Country;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDAOImpl extends AbstractHibernateDAO<Country> implements CountryDAO {
    public CountryDAOImpl() {
        super();
        setClazz(Country.class);
    }
}
