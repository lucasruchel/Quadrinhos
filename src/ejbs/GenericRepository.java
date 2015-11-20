package ejbs;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

/**
 * Created by wheezy on 20/11/15.
 */

@Stateless
@Local(basicOperations.class)
public class GenericRepository<T> implements basicOperations<T>{

    @PersistenceContext(name = "CrudPU")
    EntityManager entityManager;


    private Class<T> entityClass;

    public GenericRepository(){
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    @Override
    public T inserir(T object) {
        entityManager.find(entityClass,object);

        return object;
    }

    @Override
    public T remover(T object) {
        object = this.entityManager.merge(object);

        this.entityManager.remove(object);

        return object;
    }

    @Override
    public void update(int id, T object) {

    }

    @Override
    public T find(int id) {
        return this.entityManager.find(entityClass, id);
    }
}
