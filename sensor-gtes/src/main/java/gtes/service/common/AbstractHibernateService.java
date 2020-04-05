package gtes.service.common;

import gtes.dao.common.IOperations;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

//@Transactional (value="hibernateTransactionManager")

public abstract class AbstractHibernateService<E extends Serializable> extends AbstractService<E> implements IOperations<E> {
    @Override
    @Transactional
    public E findOne(final int id){return super.findOne(id);}
    @Override
    @Transactional
    public E findName(final String name){return super.findName(name);}
    @Override
    @Transactional
    public List<E> findAll(){return super.findAll();}
    @Override
    @Transactional
    public void save(final E entity){
        super.save(entity);
    }
    @Override
    @Transactional
    public void delete(final E entity){super.delete(entity);}
    @Override
    @Transactional
    public void deleteById(final int id){
        super.deleteById(id);
    }
    @Override
    @Transactional
    public void deleteByName(final String name){super.deleteByName(name);}



}
