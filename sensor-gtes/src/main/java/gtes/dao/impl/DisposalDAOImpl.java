package gtes.dao.impl;

import gtes.dao.DisposalDAO;
import gtes.dao.common.AbstractHibernateDAO;
import gtes.entity.Disposal;
import gtes.entity.Disposal_;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import static gtes.entity.Disposal_.dateDisposal;
import static gtes.entity.Sensor_.sensorNumb;

@Repository
public class DisposalDAOImpl extends AbstractHibernateDAO<Disposal> implements DisposalDAO {
    public DisposalDAOImpl() {
        super();
        setClazz(Disposal.class);
    }

    @Override
    public List<Disposal> findDisposalSensorNumber(HashMap mapPredicates) {
        CriteriaBuilder criteriaBuilder=getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Disposal> disposalCriteria=criteriaBuilder.createQuery(Disposal.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Disposal> disposalRoot = disposalCriteria.from(Disposal.class);
        disposalCriteria.select(disposalRoot);
        if (mapPredicates.get("sensorNumb")!=null){
            predicates.add(criteriaBuilder.equal(disposalRoot.get(Disposal_.numberSensor),mapPredicates.get("sensorNumb")));
        }
        disposalCriteria.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        disposalCriteria.orderBy(criteriaBuilder.desc(disposalRoot.get(dateDisposal)));

        return getCurrentSession().createQuery(disposalCriteria).getResultList();
    }

    @Override
    public List<String> numberList() {
        Query query=getCurrentSession().createQuery("select distinct d.numberSensor from Disposal d");
        return query.list();
    }
}
