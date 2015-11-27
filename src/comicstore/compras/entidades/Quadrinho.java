package comicstore.compras.entidades;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wheezy on 19/11/15.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Quadrinho.findByQuadrinho", query = "select q from Quadrinho q"),
        @NamedQuery(name = "Quadrinho.findByQuadrinhoFiltro", query = "select q from Quadrinho q where q.nome Like :campo "+
                                                                                                        "or q.descricao Like :campo "+
                                                                                                        "or q.estado Like :campo "+
                                                                                                        "or q.genero Like :campo")
})
public class Quadrinho implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome;

    @Lob
    private String descricao;

    private String imagePath;

    private float valorCompra;

    private float valorVenda;

    private int nroPaginas;

    private String genero;

    private String faixaEtaria;

    @Enumerated(EnumType.STRING)
    private QuadrinhoEstado estado;


    @Temporal(TemporalType.TIMESTAMP)
    private Date dtLancamento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAlteracao;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
            this.valorVenda = valorVenda;

    }

    public int getNroPaginas() {
        return nroPaginas;
    }

    public void setNroPaginas(int nroPaginas) {
        this.nroPaginas = nroPaginas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public QuadrinhoEstado getEstado() {
        return estado;
    }

    public void setEstado(QuadrinhoEstado estado) {
        this.estado = estado;
    }

    public Date getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(Date dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public Date getDtAlteracao() {
        return dtAlteracao;
    }

    public void setDtAlteracao(Date dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }
}
