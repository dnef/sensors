package gtes.dao.impl;

import gtes.dao.ModelSensorDAO;
import gtes.dao.common.AbstractHibernateDAO;
import gtes.entity.ModelSensor;
import org.springframework.stereotype.Repository;

@Repository
public class ModelSensorDAOImpl extends AbstractHibernateDAO<ModelSensor> implements ModelSensorDAO {
    public ModelSensorDAOImpl(){
        super();
        setClazz(ModelSensor.class);
    }
}
