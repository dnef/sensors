package gtes.service.impl;

import gtes.dao.LocationInstallDAO;
import gtes.dao.common.IOperations;
import gtes.entity.LocationInstall;
import gtes.service.LocationInstallService;
import gtes.service.common.AbstractHibernateService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class LocationInstallServiceImpl extends AbstractHibernateService<LocationInstall> implements LocationInstallService {
    @Autowired
    private LocationInstallDAO dao;
    public LocationInstallServiceImpl() {
        super();
    }
    @Override
    protected IOperations<LocationInstall> getDao(){return dao;}


    @Override
    @Transactional
    public List<LocationInstall> findInstallLocation(int selectLocationId) {//TODO Criteria
        return dao.findInstallLocation(selectLocationId);
    }

    @Override
    @Transactional
    public List<LocationInstall> findInstallSensor(int selectSensorId) {
        return dao.findInstallSensor(selectSensorId);
    }
}
