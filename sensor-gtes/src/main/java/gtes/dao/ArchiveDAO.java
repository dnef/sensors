package gtes.dao;

import gtes.dao.common.IGenericDAO;
import gtes.entity.Archive;

import java.util.HashMap;
import java.util.List;


public interface ArchiveDAO extends IGenericDAO<Archive> {
    List<Archive> findArchiveBySensorId(int selectSensorId);//TODO Criteria добавить в Abstract

    interface UserProfileDAO {
    }
    List<Archive> findArchiveWhere(HashMap mapPredicates);
}
