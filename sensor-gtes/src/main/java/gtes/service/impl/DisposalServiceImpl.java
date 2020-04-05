package gtes.service.impl;

import gtes.dao.DisposalDAO;
import gtes.dao.common.IOperations;
import gtes.entity.Disposal;
import gtes.service.DisposalService;
import gtes.service.common.AbstractHibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class DisposalServiceImpl extends AbstractHibernateService<Disposal> implements DisposalService {
    @Autowired
    private DisposalDAO dao;

    public DisposalServiceImpl() {super();
    }
    @Override
    protected IOperations<Disposal> getDao(){return dao;}

    @Override
    @Transactional
    public List<Disposal> findDisposalSensorNumber(HashMap mapPredicates) {
        return dao.findDisposalSensorNumber(mapPredicates);
    }

    @Override
    @Transactional
    public List<String> numberList() {
        return dao.numberList();
    }
}
