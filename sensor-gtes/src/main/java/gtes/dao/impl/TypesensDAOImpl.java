package gtes.dao.impl;

import gtes.dao.TypesensDAO;
import gtes.dao.common.AbstractHibernateDAO;
import gtes.entity.Typesens;
import org.springframework.stereotype.Repository;

@Repository
public class TypesensDAOImpl extends AbstractHibernateDAO <Typesens> implements TypesensDAO {
    public TypesensDAOImpl (){
        super();
        setClazz(Typesens.class);
    }

}
