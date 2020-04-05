package gtes.dao.impl;

import gtes.dao.LocationDAO;
import gtes.dao.common.AbstractHibernateDAO;
import gtes.entity.Location;
import org.springframework.stereotype.Repository;

@Repository
public class LocationDAOImpl extends AbstractHibernateDAO<Location> implements LocationDAO {
    public LocationDAOImpl() {
        super();
        setClazz(Location.class);
    }
}
