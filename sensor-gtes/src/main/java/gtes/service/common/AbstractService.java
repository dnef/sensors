package gtes.service.common;


import gtes.dao.common.IOperations;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<E extends Serializable> implements IOperations<E> {
    protected abstract IOperations<E> getDao();

    @Override
    public E findOne(final int id){
        return getDao().findOne(id);
    }
    @Override
    public E findName(final String name){return getDao().findName(name);}
    @Override
    public List<E> findAll(){
        return getDao().findAll();
    }
    @Override
    public void save (final E entity){
        getDao().save(entity);
    }
    @Override
    public void delete(final E entity){
        getDao().delete(entity);};
    @Override
    public void deleteById(final int id){
        getDao().deleteById(id);};
    @Override
    public void deleteByName(final String name){
        getDao().deleteByName(name);};
}
