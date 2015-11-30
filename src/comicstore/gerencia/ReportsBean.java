package comicstore.gerencia;

import comicstore.autenticacao.ejbs.ClienteRepository;
import comicstore.autenticacao.entidades.Cliente;
import comicstore.compras.ejbs.QuadrinhoRepository;
import comicstore.compras.entidades.Compra;
import comicstore.compras.entidades.CompraProduto;
import comicstore.compras.entidades.Quadrinho;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wagner on 27/11/15.
 */
@Named
@SessionScoped
public class ReportsBean implements Serializable{

    private String campo="";
    private String order="";

    @EJB
    ClienteRepository clienteRepository;

    @EJB
    QuadrinhoRepository quadrinhoRepository;

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public List<String> getOrders(){
        List<String> order = new ArrayList<String>();

        order.add("id");
        order.add("nome");
        order.add("descricao");
        order.add("valorCompra");
        order.add("valorVenda");
        order.add("nroPaginas");
        order.add("genero");
        order.add("estoque");
        order.add("faixaEtaria");

        return order;
    }

    public List<Cliente> buscaClienteCampo(){
        
        if(campo.isEmpty())
            return clienteRepository.findWithNamedQuery("Cliente.findByCliente");
        else {
            Map parameters = new HashMap<String, Object>();
            parameters.put("campo", "%" + campo.trim().toLowerCase() + "%");
            return clienteRepository.findWithNamedQuery("Cliente.findByClienteFiltro", parameters);
        }
    }


    public List<Quadrinho> buscaQuadrinhoCampo(){
        System.out.println(campo+" "+order);
        if(campo.isEmpty() || order.isEmpty())
            return quadrinhoRepository.findWithNamedQuery("Quadrinho.findByQuadrinho");
        else{
            Map parameters = new HashMap<String,Object>();
            parameters.put("campo","%"+campo.trim().toLowerCase()+"%");
            parameters.put("order",order.trim().toLowerCase());
            System.out.println(order);
            return quadrinhoRepository.findWithNamedQuery("Quadrinho.findByQuadrinhoFiltro",parameters);
        }
    }
    public List<Compra> buscaCompras(){
            return quadrinhoRepository.findWithNamedQuery("Compra.findByCompras");
    }

}
