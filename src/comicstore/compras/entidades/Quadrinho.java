package comicstore.compras.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

}
