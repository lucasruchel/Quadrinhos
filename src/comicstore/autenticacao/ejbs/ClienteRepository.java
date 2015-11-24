package comicstore.autenticacao.ejbs;

import comicstore.autenticacao.entidades.Cliente;
import comicstore.utils.repository.GenericRepository;

import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * Created by wagner on 18/11/15.
 */
@Stateless
public class ClienteRepository extends GenericRepository<Cliente> {



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
