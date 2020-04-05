package gtes.service.impl;

import gtes.dao.TypesensDAO;
import gtes.dao.common.IOperations;
import gtes.entity.Typesens;
import gtes.service.TypesensService;
import gtes.service.common.AbstractHibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypesensServiceImpl extends AbstractHibernateService<Typesens> implements TypesensService {
    @Autowired
    //@Qualifier("typesensDao")
    private TypesensDAO dao;

    public TypesensServiceImpl(){ super();}
    @Override
    protected IOperations<Typesens> getDao(){
        return dao;
    }

}
