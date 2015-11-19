package ejbs;

import entidades.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;

/**
 * Created by wagner on 18/11/15.
 */
@Stateless
public class EjbCliente {

    @PersistenceContext(name = "CrudPU")
    EntityManager entityManager;

    public void persist(Cliente cliente){
        entityManager.persist(cliente);
    }
    public Cliente find(int id){
        return entityManager.find(Cliente.class,id);
    }

    public Cliente findByEmailSenha(String email,String senha) {
        try {
            Query query = entityManager.createNamedQuery("Cliente.login");
            query.setParameter("email", email);
            query.setParameter("senha", senha);

            Cliente cliente = (Cliente) query.getSingleResult();
            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
