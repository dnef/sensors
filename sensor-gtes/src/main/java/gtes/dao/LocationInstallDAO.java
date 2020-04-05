package gtes.dao;

import gtes.dao.common.IGenericDAO;
import gtes.entity.LocationInstall;

import java.util.List;

public interface LocationInstallDAO extends IGenericDAO<LocationInstall>{
    List<LocationInstall> findInstallLocation(int selectLocationId);//TODO подумать о criteria Query DSL
    List<LocationInstall> findInstallSensor(int selectSensorId);
}
