package gtes.service;

import com.google.common.collect.HashMultimap;
import gtes.dao.common.IOperations;
import gtes.entity.Sensor;

import java.util.HashMap;
import java.util.List;

public interface SensorService extends IOperations<Sensor> {
    List<Sensor> findSensorByNumber(String selectSensorNumber);//TODO Criteria
    List<Sensor> findSensorWhere (HashMultimap<String, Object> mapPredicates);
    boolean isNumberSensorUnique(Integer id, String sensorNumb);
}
