package comicstore.compras.ejbs;

import comicstore.compras.entidades.Compra;
import comicstore.utils.repository.GenericRepository;

import javax.ejb.Stateless;

/**
 * Created by wagner on 28/11/15.
 */

@Stateless
public class ComprasRepository extends GenericRepository<Compra> {}
