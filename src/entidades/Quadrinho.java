package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wheezy on 19/11/15.
 */

@Entity
public class Quadrinho implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome;

    @Lob
    private String descricao;

    @Lob
    private byte[] imagem;


    private float valorCompra;

    private float valorVenda;

    private int nroPaginas;

    @OneToMany
    private List<Genero> genero;

    @ManyToOne
    private FaixaEtaria faixaEtaria;

    @Enumerated(EnumType.STRING)
    private QuadrinhoEstado estado;

    private Date dtLancamento;
    private Date dtAlteracao;

}
