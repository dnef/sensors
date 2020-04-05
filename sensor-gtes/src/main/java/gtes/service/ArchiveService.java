package gtes.service;

import gtes.dao.common.IOperations;
import gtes.entity.Archive;
import gtes.entity.Sensor;

import java.util.HashMap;
import java.util.List;

public interface ArchiveService extends IOperations<Archive> {
    List<Archive> findArchiveBySensorId(int selectSensorId);//TODO Criteria
    List<Archive> findArchiveWhere (HashMap mapPredicates);
}
