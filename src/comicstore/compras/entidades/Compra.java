package comicstore.compras.entidades;

import comicstore.autenticacao.entidades.Cliente;
import org.omg.CORBA.PERSIST_STORE;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by wheezy on 27/11/15.
 */
@Entity
@NamedQueries({
        @NamedQuery(name="Compra.findByCompras", query="Select c FROM Compra c")})
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne()
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CompraProduto> produtos;

    private boolean quitada=false;

    private boolean entregue=false;

    public Date getDtCompra() {
        return dtCompra;
    }

    public void setDtCompra(Date dtCompra) {
        this.dtCompra = dtCompra;
    }

    @Temporal(TemporalType.DATE)

    private Date dtCompra;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<CompraProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<CompraProduto> produtos) {
        this.produtos = produtos;
    }

    public boolean isQuitada() {
        return quitada;
    }

    public void setQuitada(boolean quitada) {
        this.quitada = quitada;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }
}
