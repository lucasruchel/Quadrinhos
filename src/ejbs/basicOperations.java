package ejbs;

import javax.ejb.Local;

/**
 * Created by wheezy on 20/11/15.
 */

@Local
public interface basicOperations<T> {

    T inserir(T object);

    T remover(T object);

    void update(int id, T object);

    T find(int id);
}
