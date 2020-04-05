package gtes.dao;

import com.google.common.collect.HashMultimap;
import gtes.dao.common.IGenericDAO;
import gtes.entity.Sensor;

import java.util.HashMap;
import java.util.List;

public interface SensorDAO extends IGenericDAO<Sensor> {
    List<Sensor> findSensorByNumber(String selectSensorNumber);//TODO Criteria
    List<Sensor> findSensorWhere (HashMultimap mapPredicates);
}
