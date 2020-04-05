package gtes.service;

import gtes.dao.LocationInstallDAO;
import gtes.dao.common.IOperations;
import gtes.entity.LocationInstall;

import java.util.List;


public interface LocationInstallService extends IOperations<LocationInstall> {
    List<LocationInstall> findInstallLocation(int selectLocationId);//TODO Criteria
    List<LocationInstall> findInstallSensor(int selectSensorId);
}
