package gtes.dao.common;

import com.google.common.base.Preconditions;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractDAO<E extends Serializable> implements IOperations<E> {

    protected Class<E> clazz;

    protected final void setClazz(final Class<E> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

//    protected final void setClazz(final Class<E> clazzToSet) {
//        clazz = (Class<E>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//    }
}