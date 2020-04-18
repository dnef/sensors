package gtes.dao.impl;

import gtes.dao.common.security.AbstractSecurityDAO;
import gtes.entity.PersistentLogin;
import gtes.entity.PersistentLogin_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractSecurityDAO<String, PersistentLogin> implements PersistentTokenRepository {
    static final Logger logger = LogManager.getLogger(HibernateTokenRepositoryImpl.class.getName());

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        logger.info("Creating Token for user : {}", token.getUsername());
        System.out.println("newToken");
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLastUsed(token.getDate());
        persist(persistentLogin);
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        logger.info("Updating Token for seriesId : {}", seriesId);
        PersistentLogin persistentLogin = getByKey(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLastUsed(lastUsed);
        update(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        logger.info("Fetch Token if any for seriesId : {}", seriesId);
        try {
//            Criteria criteria = createEntityCriteria();
//            criteria.add(Restrictions.eq("series",seriesId));
//            PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();
//            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
//                    persistentLogin.getToken(), persistentLogin.getLastUsed());
            CriteriaBuilder criteriaBuilder = createSession().getCriteriaBuilder();
            CriteriaQuery<PersistentLogin> seriesIdCriteriaQuery = criteriaBuilder.createQuery(PersistentLogin.class);
            Root<PersistentLogin> persistentLoginRoot = seriesIdCriteriaQuery.from(PersistentLogin.class);
            seriesIdCriteriaQuery.select(persistentLoginRoot);
            Predicate predicate = criteriaBuilder.equal(persistentLoginRoot.get(PersistentLogin_.series), seriesId);
            seriesIdCriteriaQuery.where(predicate);
            TypedQuery<PersistentLogin> persistentLoginTypedQuery = createSession().createQuery(seriesIdCriteriaQuery);
            PersistentLogin persistentLogin = persistentLoginTypedQuery.getSingleResult();
            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLastUsed());

        } catch (NoResultException nre) {
            logger.info("Token not found...");
            return null;
        }

    }

    @Override
    public void removeUserTokens(String username) {
        logger.info("Removing Token if any for user : {}", username);
//        Criteria criteria = createEntityCriteria();
//        criteria.add(Restrictions.eq("username", username));
//        PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();
       //TODO:временное решение, logout не срабатывает если в таблице 2 токена
        CriteriaBuilder criteriaBuilder = createSession().getCriteriaBuilder();
        CriteriaQuery<PersistentLogin> persistentLoginCriteriaQuery = criteriaBuilder.createQuery(PersistentLogin.class);
        Root<PersistentLogin> persistentLoginRoot = persistentLoginCriteriaQuery.from(PersistentLogin.class);
        persistentLoginCriteriaQuery.select(persistentLoginRoot);
        Predicate predicate = criteriaBuilder.equal(persistentLoginRoot.get(PersistentLogin_.username), username);
        persistentLoginCriteriaQuery.where(predicate);
        TypedQuery<PersistentLogin> persistentLoginTypedQuery = createSession().createQuery(persistentLoginCriteriaQuery);
        try {
            List<PersistentLogin> persistentLoginList = persistentLoginTypedQuery.getResultList();
                persistentLoginList.stream().forEach(persistentLogin -> delete(persistentLogin));
//            PersistentLogin persistentLogin = persistentLoginTypedQuery.getSingleResult();
//            if (persistentLogin != null) {
//                logger.info("rememberMe was selected");
//                delete(persistentLogin);
//            }
        } catch (final Exception e) {
            logger.info("Token not found...");
        }


    }
}
