package gtes.dao.impl;


import gtes.dao.UserDAO;
import gtes.dao.common.security.AbstractSecurityDAO;
import gtes.entity.User;
import gtes.entity.User_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl extends AbstractSecurityDAO<Integer, User> implements UserDAO {
    static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

    @Override
    public User findById(int id) {
        User user = getByKey(id);
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    @Override
    public User findBySSO(String sso) {
        logger.info("SSO : {}", sso);
//        Criteria criteria = createEntityCriteria();
//        criteria.add(Restrictions.eq("ssoId", sso));
//        User user = (User) criteria.uniqueResult();
        try {
            CriteriaBuilder criteriaBuilder = createSession().getCriteriaBuilder();
            CriteriaQuery<User> ssoCriteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = ssoCriteriaQuery.from(User.class);
            ssoCriteriaQuery.select(userRoot);
            Predicate predicate = criteriaBuilder.equal(userRoot.get(User_.ssoId), sso);
            ssoCriteriaQuery.where(predicate);
            List<User> userList = createSession().createQuery(ssoCriteriaQuery).getResultList();
            User user = createSession().createQuery(ssoCriteriaQuery).getSingleResult();

            if (user != null) {
                Hibernate.initialize(user.getUserProfiles());
            }
            return user;
        }catch (Exception e){return null;}
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        CriteriaBuilder criteriaBuilder = createSession().getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userRoot);
//        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//без дубликатов
        List<User> users = (List<User>) createSession().createQuery(userCriteriaQuery).getResultList();
        return users;
    }

    public void save(User user) {
        logger.info("Save user: {}", user.getFirstName(), user.getSsoId());
        persist(user);
    }

    public void updateSave(User user) {
        update(user);
    }

    public void deleteBySSO(String sso) {
        User user = findBySSO(sso);
//        Criteria criteria = createEntityCriteria();
//        criteria.add(Restrictions.eq("ssoId", sso));
//        User user = (User) criteria.uniqueResult();
        delete(user);
    }

}
