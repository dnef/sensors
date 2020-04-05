package gtes.dao.impl;



import gtes.dao.LocationInstallDAO;
import gtes.dao.common.AbstractHibernateDAO;
import gtes.entity.LocationInstall;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class LocationInstallDAOImpl extends AbstractHibernateDAO<gtes.entity.LocationInstall> implements LocationInstallDAO {
    public LocationInstallDAOImpl() {
        super();
        setClazz(LocationInstall.class);
    }

    @Override
    public List<LocationInstall> findInstallLocation(int selectLocationId) {//TODO Criteria
        Query query = getCurrentSession().createQuery("from LocationInstall where locationId =:LocationIdParam");
        query.setParameter("LocationIdParam",selectLocationId);
        return query.list();
    }

    @Override
    public List<LocationInstall> findInstallSensor(int selectSensorId) {
        Query query = getCurrentSession().createQuery("from LocationInstall where sensorId =:SensorIdParam");
        query.setParameter("SensorIdParam",selectSensorId);
        return query.list();
    }
}
