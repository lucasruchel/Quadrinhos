package comicstore.utils.repository;

import comicstore.utils.interfaces.basicOperations;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wheezy on 20/11/15.
 */

@Stateless
@Local(basicOperations.class)
public class GenericRepository<T> implements basicOperations<T>{

    @PersistenceContext(name = "CrudPU")
    public EntityManager entityManager;


    @Override
    public T create(T object) {
        this.entityManager.persist(object);
        this.entityManager.flush();
        this.entityManager.refresh(object);

        return object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T find(Class type, int id) {
        return (T) this.entityManager.find(type,id);
    }

    @Override
    public T update(T object) {
        return this.entityManager.merge(object);
    }

    @Override
    public void delete(Class type, Object id) {
        Object o = this.entityManager.getReference(type,id);

        this.entityManager.remove(o);
    }

    @Override
    public List findWithNamedQuery(String namedQueryName){
        return this.entityManager.createNamedQuery(namedQueryName).getResultList();
    }

    @Override
    public List findWithNamedQuery(String namedQueryName, Map parameters){
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    @Override
    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit) {
        Set<Map.Entry> rawParameters = parameters.entrySet();

        Query query = this.entityManager.createNamedQuery(namedQueryName);

        if(resultLimit > 0)
            query.setMaxResults(resultLimit);
        for (Map.Entry<String,Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }


    @Override
    public List findWithNamedQuery(String queryName, int resultLimit) {
        return this.entityManager.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }


    public List findByNativeQuery(String sql, Class type) {
        return this.entityManager.createNativeQuery(sql, type).getResultList();
    }


}
