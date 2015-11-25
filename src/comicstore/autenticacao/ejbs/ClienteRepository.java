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

}
