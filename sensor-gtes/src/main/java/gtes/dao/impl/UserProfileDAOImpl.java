package gtes.dao.impl;


import gtes.dao.UserProfileDAO;
import gtes.dao.common.security.AbstractSecurityDAO;
import gtes.entity.UserProfile;
import gtes.entity.UserProfile_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("userProfileDao")
public class UserProfileDAOImpl extends AbstractSecurityDAO<Integer,UserProfile>implements UserProfileDAO {
    @Override
    @SuppressWarnings("unchecked")
    public List<UserProfile> findAll(){
        //        Criteria criteria = createEntityCriteria();
//        criteria.addOrder(Order.asc("type"));
//        return (List<UserProfile>)criteria.list();
        CriteriaBuilder criteriaBuilder=createSession().getCriteriaBuilder();
        CriteriaQuery<UserProfile> userProfileCriteriaQuery = criteriaBuilder.createQuery(UserProfile.class);
        Root<UserProfile> userProfileRoot=userProfileCriteriaQuery.from(UserProfile.class);
        userProfileCriteriaQuery.select(userProfileRoot);
        userProfileCriteriaQuery.orderBy(criteriaBuilder.asc(userProfileRoot.get(UserProfile_.type)));
        return (List<UserProfile>) createSession().createQuery(userProfileCriteriaQuery).getResultList();
    }

    @Override
    public UserProfile findByType(String type) {
//        Criteria criteria = createEntityCriteria();
//        criteria.add(Restrictions.eq("type", type));
//        return (UserProfile) criteria.uniqueResult();
        CriteriaBuilder criteriaBuilder=createSession().getCriteriaBuilder();
        CriteriaQuery<UserProfile> typeCriteriaQuery = criteriaBuilder.createQuery(UserProfile.class);
        Root<UserProfile> typeRoot=typeCriteriaQuery.from(UserProfile.class);
        typeCriteriaQuery.select(typeRoot);
        Predicate predicate= criteriaBuilder.equal(typeRoot.get(UserProfile_.type),type);
        typeCriteriaQuery.where(predicate);
        UserProfile userProfile = createSession().createQuery(typeCriteriaQuery).getSingleResult();
        return userProfile;
    }

    @Override
    public UserProfile findById(int id) {
        return getByKey(id);
    }

}
