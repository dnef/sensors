package gtes.dao.impl;

import gtes.dao.ArchiveDAO;
import gtes.dao.common.AbstractHibernateDAO;
import gtes.entity.Archive;
import gtes.entity.Archive_;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ArchiveDAOImpl extends AbstractHibernateDAO<Archive> implements ArchiveDAO {
    public ArchiveDAOImpl() {
        super();
        setClazz(Archive.class);
    }

    @Override
    public List<Archive> findArchiveBySensorId(int selectSensorId) {
        Query query = getCurrentSession().createQuery("from Archive where sensorId =:SensorIdParam");
        query.setParameter("SensorIdParam",selectSensorId);
        return query.list();
    }

    @Override
    public List<Archive> findArchiveWhere(HashMap mapPredicates) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Archive> archiveCriteriaQuery = criteriaBuilder.createQuery(Archive.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Archive> archiveRoot = archiveCriteriaQuery.from(Archive.class);
        archiveCriteriaQuery.select(archiveRoot);
        if (mapPredicates.get("locationId") != null){
            predicates.add(criteriaBuilder.equal(archiveRoot.get(Archive_.locationId),mapPredicates.get("locationId")));
        }
        if (mapPredicates.get("sensorId") != null){
            predicates.add(criteriaBuilder.equal(archiveRoot.get(Archive_.sensorId),mapPredicates.get("sensorId")));
        }
        if (mapPredicates.get("dateStart") !=null & mapPredicates.get("dateStop") !=null){
            predicates.add(criteriaBuilder.between(archiveRoot.<LocalDateTime> get(Archive_.installDate), (LocalDateTime) mapPredicates.get("dateStart"), (LocalDateTime) mapPredicates.get("dateStop")));
        }

        archiveCriteriaQuery.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        archiveCriteriaQuery.orderBy(criteriaBuilder.desc(archiveRoot.get(Archive_.idArchive)),criteriaBuilder.desc(archiveRoot.get(Archive_.installDate)));

        return getCurrentSession().createQuery(archiveCriteriaQuery).getResultList();
    }

}
