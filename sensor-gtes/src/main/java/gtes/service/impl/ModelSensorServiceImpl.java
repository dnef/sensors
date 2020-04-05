package gtes.service.impl;

import gtes.dao.ModelSensorDAO;
import gtes.dao.common.IOperations;
import gtes.entity.ModelSensor;
import gtes.service.ModelSensorService;
import gtes.service.common.AbstractHibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelSensorServiceImpl extends AbstractHibernateService<ModelSensor> implements ModelSensorService {
    @Autowired
    private ModelSensorDAO dao;
    public ModelSensorServiceImpl(){super();}
    @Override
    protected IOperations<ModelSensor> getDao() {return dao;}
}
