package gtes.service.impl;

import gtes.dao.UnitDAO;
import gtes.dao.common.IOperations;
import gtes.entity.Unit;
import gtes.service.UnitService;
import gtes.service.common.AbstractHibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl extends AbstractHibernateService<Unit> implements UnitService {
    @Autowired
    private UnitDAO dao;
    public UnitServiceImpl(){super();}
    @Override
    protected IOperations<Unit> getDao(){return dao;}
}
