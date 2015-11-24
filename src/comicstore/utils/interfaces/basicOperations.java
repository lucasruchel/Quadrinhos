package comicstore.utils.interfaces;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

/**
 * Created by wheezy on 20/11/15.
 */

@Local
public interface basicOperations<T> {

    T create(T object);

    T find(Class type, int id);

    T update(T object);

    void delete(Class type, Object id);

    List findWithNamedQuery(String queryName);

    List findWithNamedQuery(String queryName, int resultLimit);

    List findWithNamedQuery(String namedQueryName, Map parameters);

    List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit);

}
