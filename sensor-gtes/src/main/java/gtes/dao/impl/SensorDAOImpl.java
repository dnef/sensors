package gtes.dao.impl;

import com.google.common.collect.HashMultimap;
import gtes.dao.SensorDAO;
import gtes.dao.common.AbstractHibernateDAO;
import gtes.entity.Sensor;
import gtes.entity.Sensor_;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static gtes.entity.Sensor_.*;

@Repository
public class SensorDAOImpl extends AbstractHibernateDAO<Sensor> implements SensorDAO {
    public SensorDAOImpl() {
        super();
        setClazz(Sensor.class);
    }

//    public CriteriaBuilder getCriteriaBuilder() {return getCurrentSession().getCriteriaBuilder();}

    @Override
    public List<Sensor> findSensorByNumber(String selectSensorNumber) {
        Query query = getCurrentSession().createQuery("from Sensor where sensorNumb =:SensorNumber");
        query.setParameter("SensorNumber", selectSensorNumber.toLowerCase());
        return query.list();
    }

    //TODO Criteria описать в AbstractHibernate
    @Override
    public List<Sensor> findSensorWhere(HashMultimap mapPredicates) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Sensor> sensorCriteria = criteriaBuilder.createQuery(Sensor.class);
        List<Predicate> predicates = new ArrayList<>(); //
        List<Predicate> locationPredicate = new ArrayList<>(); //
        Root<Sensor> sensorRoot = sensorCriteria.from(Sensor.class);
        sensorCriteria.select(sensorRoot);

        if (!mapPredicates.get("modelId").toString().equals("[]")) {
            Object[] model = mapPredicates.get("modelId").toArray();
            predicates.add(criteriaBuilder.equal(sensorRoot.get(modelId), model[0]));
// ?????           predicates.add(criteriaBuilder.equal(sensorRoot.get(modelId), mapPredicates.get("modelId")));
        }
//выбор нескольких установок
//        Predicate loc1=criteriaBuilder.or(criteriaBuilder.equal(sensorRoot.get(locationId), 2));
//        Predicate loc2=criteriaBuilder.or(criteriaBuilder.equal(sensorRoot.get(locationId), 3));
//        predicates.add(criteriaBuilder.or(loc1,loc2));
        if (!mapPredicates.get("locationId").toString().equals("[]")) {

            Set setLocation = mapPredicates.get("locationId");
            setLocation.forEach((setLoc) -> {
                locationPredicate.add(criteriaBuilder.or(criteriaBuilder.equal(sensorRoot.get(locationId), setLoc)));
            });
            predicates.add(criteriaBuilder.or(locationPredicate.toArray(new Predicate[]{})));
        }
        if (!mapPredicates.get("sensorNumb").toString().equals("[]")) {
            predicates.add(criteriaBuilder.equal(sensorRoot.get(sensorNumb), mapPredicates.get("sensorNumb")));
        }
        if (!mapPredicates.get("verification").toString().equals("[]")) {
            predicates.add(criteriaBuilder.equal(sensorRoot.get(verification), mapPredicates.get("verification")));
        }
        sensorCriteria.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        sensorCriteria.orderBy(criteriaBuilder.asc(sensorRoot.get(locationId)), criteriaBuilder.asc(sensorRoot.get(modelId)));
        return getCurrentSession().createQuery(sensorCriteria).getResultList();
    }
}
