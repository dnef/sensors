package gtes.service.impl;

import com.google.common.collect.HashMultimap;
import gtes.dao.SensorDAO;
import gtes.dao.common.IOperations;
import gtes.entity.Sensor;
import gtes.service.SensorService;
import gtes.service.common.AbstractHibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static gtes.entity.Sensor_.*;
import static gtes.entity.Sensor_.verification;

@Service
public class SensorServiceImpl extends AbstractHibernateService<Sensor> implements SensorService {
    @Autowired
    private SensorDAO dao;

    public SensorServiceImpl() { super(); }
    protected IOperations<Sensor> getDao(){return dao;}

    @Override
    @Transactional
    public List<Sensor> findSensorByNumber(String selectSensorNumber) {
        return dao.findSensorByNumber(selectSensorNumber);
    }

    @Override
    @Transactional
    public List<Sensor> findSensorWhere(HashMultimap<String, Object> mapPredicates) {
        return dao.findSensorWhere(mapPredicates);
    }

    @Override
    @Transactional
    public boolean isNumberSensorUnique(Integer id, String sensorNumb) {
        List<Sensor> sensor = dao.findSensorByNumber(sensorNumb); //TODO убрать нахер List<> из findSensorByNumber один черт одинаковых не может быть
        if (sensor.isEmpty()){
            return true;
        }
        if (sensor.size()>1){
            return false;
        } else{
            Sensor sensorIf = sensor.get(0);
            return (sensorIf.getIdSensor()==id);
        }
    }

}
