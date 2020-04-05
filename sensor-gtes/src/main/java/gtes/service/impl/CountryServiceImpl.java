package gtes.service.impl;

import gtes.dao.CountryDAO;
import gtes.dao.common.IOperations;
import gtes.entity.Country;
import gtes.service.CountryService;
import gtes.service.common.AbstractHibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends AbstractHibernateService<Country> implements CountryService {
    @Autowired
    //@Qualifier("typesensDao")
    private CountryDAO dao;

    public CountryServiceImpl(){ super();}
    @Override
    protected IOperations<Country> getDao(){
        return dao;
    }

}
