package gtes.service.impl;

import gtes.dao.LocationDAO;
import gtes.dao.common.IOperations;
import gtes.entity.Location;
import gtes.service.LocationService;
import gtes.service.common.AbstractHibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl extends AbstractHibernateService<Location> implements LocationService {
    @Autowired
    private LocationDAO dao;

    public LocationServiceImpl() {super();
    }
    @Override
    protected IOperations<Location> getDao(){
        return dao;
    }
}
