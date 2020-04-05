package gtes.service.impl;

import gtes.dao.FirmDAO;
import gtes.dao.common.IOperations;
import gtes.entity.Firm;
import gtes.service.FirmService;
import gtes.service.common.AbstractHibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirmServiceImpl extends AbstractHibernateService<Firm> implements FirmService {
    @Autowired
    private FirmDAO dao;

    public FirmServiceImpl() {super();
    }
    protected IOperations<Firm> getDao(){
        return dao;
    }
}
