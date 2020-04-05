package gtes.service.impl;

import gtes.dao.ArchiveDAO;
import gtes.dao.common.IOperations;
import gtes.entity.Archive;
import gtes.entity.Sensor;
import gtes.service.ArchiveService;
import gtes.service.common.AbstractHibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class ArchiveServiceImpl extends AbstractHibernateService<Archive> implements ArchiveService {
    @Autowired
    private ArchiveDAO dao;

    public ArchiveServiceImpl() {super();
    }
    @Override
    protected IOperations<Archive> getDao(){return dao;}

    @Override
    @Transactional
    public List<Archive> findArchiveBySensorId(int selectSensorId) {
        return dao.findArchiveBySensorId(selectSensorId);
    }

    @Override
    @Transactional
    public List<Archive> findArchiveWhere(HashMap mapPredicates) {
        return dao.findArchiveWhere(mapPredicates);
    }
}
