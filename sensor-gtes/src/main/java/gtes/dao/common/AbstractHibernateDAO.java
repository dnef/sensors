package gtes.dao.common;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;


public abstract class AbstractHibernateDAO<E extends Serializable> extends AbstractDAO<E> implements IOperations<E> {
    // private Class<E> clazz;
    @Autowired
    SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

//    public final void setClazz(Class<E> clazzToSet){
//        this.clazz = clazzToSet;
//    }

    public E findOne(int id) {
        return (E) getCurrentSession().get(clazz, id);
    }

    public E findName(String name) {
        return (E) getCurrentSession().get(clazz, name);
    }

    public List<E> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
    }

    public void save(E entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(E entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(int id) {
        E entity = findOne(id);
        delete(entity);
    }

    public void deleteByName(String name) {
        E entity = findName(name);
        delete(entity);
    }


    /////////////////////////
    //Сылка пример общего criteria, решил не делать.
    //https://www.programcreek.com/java-api-examples/?code=janhalasa/JpaCriteriaWithLambdaExpressions/JpaCriteriaWithLambdaExpressions-master/src/main/java/com/halasa/criterialambda/dao/AbstractDao.java


}
