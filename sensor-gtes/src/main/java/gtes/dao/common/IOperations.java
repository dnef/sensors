package gtes.dao.common;


import org.springframework.security.access.annotation.Secured;

import java.io.Serializable;
import java.util.List;

public interface IOperations <E extends Serializable>{
    E findOne(final int id);
    E findName(final String name);
    List<E> findAll();
    @Secured({"ROLE_ADMIN","ROLE_KIP"})
    void save (final E entity);
    @Secured({"ROLE_ADMIN","ROLE_KIP"})
    void delete(final E entity);
    @Secured({"ROLE_ADMIN","ROLE_KIP"})
    void deleteById(final int id);
    @Secured({"ROLE_ADMIN"})
    void deleteByName(final String name);

}
