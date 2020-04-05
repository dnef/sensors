package gtes.dao.impl;

import gtes.dao.FirmDAO;
import gtes.dao.common.AbstractHibernateDAO;
import gtes.entity.Firm;
import org.springframework.stereotype.Repository;

@Repository
public class FirmDAOImpl extends AbstractHibernateDAO<Firm> implements FirmDAO {
    public FirmDAOImpl() {
        super();
        setClazz(Firm.class);
    }
}
