package gtes.dao.common;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)

public class GenericHibernateDAO<E extends Serializable> extends AbstractHibernateDAO<E> implements IGenericDAO<E> {
}
