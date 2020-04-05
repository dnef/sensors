package gtes.dao.common.security;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractSecurityDAO<PK extends Serializable,E> {
    private final Class<E> persistentClass;
    @SuppressWarnings("unchecked")
    public AbstractSecurityDAO(){
        this.persistentClass=(Class<E>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    }
    @Autowired
    private SessionFactory sessionFactory;
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    @SuppressWarnings("unchecked")
    public E getByKey(PK key){
        return (E) getSession().get(persistentClass,key);
    }
    public void persist(E entity) {
        getSession().persist(entity);
    }
//    public void persist(E entity) {
//        getSession().saveOrUpdate(entity);
//    }
    public void update(E entity) {
        getSession().update(entity);
    }

    public void delete(E entity) {
        getSession().delete(entity);
    }

//    protected Criteria createEntityCriteria(){
//        return getSession().createCriteria(persistentClass);
//    }
protected Session createSession(){
        return getSession();
}



}
