package entidades;

import modelo.Cpf;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wagner on 17/11/15.
 */
@Entity
@NamedQueries({
        @NamedQuery(name="Cliente.login", query="Select c FROM Cliente c where c.email=:email and c.senha=:senha")})

@ManagedBean(name = "cliente")
public class Cliente  implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_cliente;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;
    private Date dataCadastro;
    private Date dataAlteracao;
    @Column(nullable=false)
    private String cpf;
    private String telefone;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String numero;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getId_cliente() {
            return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setNome(String nome){
        this.nome=nome;
    }
    public String getNome(){
        return this.nome;
    }

    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return this.email;
    }

    public void setSenha(String senha){
        this.senha=senha;
    }
    public String getSenha(){
        return this.senha;
    }

    public void setDataCadastro(Date dataCadastro){
        this.dataCadastro=dataCadastro;
    }
    public Date getDataCadastro(){
        return this.dataCadastro;
    }

    public void setDataAlteracao(Date dataAlteracao){
        this.dataAlteracao=dataAlteracao;
    }
    public Date getDataAlteracao(){
        return this.dataAlteracao;
    }
}
