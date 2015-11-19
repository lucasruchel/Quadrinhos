package entidades;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by wheezy on 19/11/15.
 */

@Entity
public class Quadrinho {
    private int id;
    private String nome;
    private float valorCompra;
    private float valorVenda;
    private int qtdEstoque;
    private Genero genero;
    private FaixaEtaria faixaEtaria;
    private QuadrinhoEstado estado;
    private String descricao;


    private Date dtLancamento;

}
