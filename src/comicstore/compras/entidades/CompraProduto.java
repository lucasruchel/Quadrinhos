package comicstore.compras.entidades;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by wheezy on 27/11/15.
 */
@Entity
public class CompraProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int quantidade;

    @ManyToOne
    private Quadrinho quadrinho;

    @Temporal(TemporalType.DATE)
    private Date timestamp;


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quadrinho getQuadrinho() {
        return quadrinho;
    }

    public void setQuadrinho(Quadrinho quadrinho) {
        this.quadrinho = quadrinho;
    }

    public Date getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
