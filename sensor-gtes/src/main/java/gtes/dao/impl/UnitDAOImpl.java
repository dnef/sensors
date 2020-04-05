package gtes.dao.impl;

import gtes.dao.UnitDAO;
import gtes.dao.common.AbstractHibernateDAO;
import gtes.entity.Unit;
import org.springframework.stereotype.Repository;

@Repository
public class UnitDAOImpl extends AbstractHibernateDAO<Unit> implements UnitDAO {
    public UnitDAOImpl() {
        super();
        setClazz(Unit.class);
    }
}
