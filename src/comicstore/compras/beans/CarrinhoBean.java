package comicstore.compras.beans;

import comicstore.compras.ejbs.ComprasRepository;
import comicstore.compras.entidades.Compra;
import comicstore.compras.entidades.CompraProduto;
import comicstore.compras.entidades.Quadrinho;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 28/11/15.
 */

@Named
@SessionScoped
public class CarrinhoBean implements Serializable{
    private List<CompraProduto> produtos;

    @EJB
    private ComprasRepository comprasRepository;

    @PostConstruct
    private void init(){
        produtos = new ArrayList<CompraProduto>();
    }

    private float vlTotal;
    //Adicionar nao passando quantidade default= qtd 1
    public String addtoCart(Quadrinho quadrinho){
        CompraProduto compraProduto;
        Compra compra;
            compraProduto = new CompraProduto(1, quadrinho, new Date());
            this.vlTotal += quadrinho.getValorVenda();// * quantidade se for usar
            produtos.add(compraProduto);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  "Item Adicionado Com Sucesso"));

        return null;
    }

    public String addtoCart(CompraProduto compraProduto){

        return null;
    }

    public int getSize(){return produtos.size();}
    public List<CompraProduto> listCart(){
        return produtos;
    }

    public float getVlTotal() {
        return vlTotal;
    }
    public String removetoCart(CompraProduto compraProduto){
        produtos.remove(compraProduto);
        
        return "";
    }

    
    public void setVlTotal(float vlTotal) {
        this.vlTotal =vlTotal ;
    }
    

}
