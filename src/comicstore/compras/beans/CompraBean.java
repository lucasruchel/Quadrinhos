package comicstore.compras.beans;

import comicstore.autenticacao.beans.AutenticacaoBean;
import comicstore.compras.ejbs.ComprasRepository;
import comicstore.compras.entidades.Compra;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wheezy on 29/11/15.
 */

@Named
@RequestScoped
public class CompraBean implements Serializable{

    @Inject
    private AutenticacaoBean autenticacaoBean;

    @Inject
    private CarrinhoBean carrinhoBean;

    @EJB
    private ComprasRepository comprasRepository;

    public String fechaCompra(){
/*
        Compra compra = new Compra();

        compra.setCliente(autenticacaoBean.getUsuarioLogado());
        compra.setProdutos(carrinhoBean.listCart());

        compra.setEntregue(false);
        compra.setQuitada(false);

        compra.setDtCompra(new Date());

        comprasRepository.create(compra);


*/
        return null;
   }

    public CarrinhoBean getCarrinhoBean() {
        return carrinhoBean;
    }

    public void setCarrinhoBean(CarrinhoBean carrinhoBean) {
        this.carrinhoBean = carrinhoBean;
    }
}

