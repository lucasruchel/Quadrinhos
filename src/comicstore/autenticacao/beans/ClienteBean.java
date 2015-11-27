package comicstore.autenticacao.beans;

import comicstore.autenticacao.ejbs.ClienteRepository;
import comicstore.autenticacao.entidades.Cliente;
import comicstore.utils.conversores.StringCipher;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 18/11/15.
 */
@Named
@RequestScoped
public class ClienteBean implements Serializable{

    @EJB
    ClienteRepository clienteRepository;

    @Inject
    AutenticacaoBean autenticacaoBean;

    private String estado;
    private Cliente cliente;

    private boolean isAdm;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @PostConstruct
    public void init(){
        cliente = new Cliente();
    }


    String resp = "";

    public String update() {

        if (this.cliente == null) {

            this.resp = "login não encontrado";
            return this.resp;
        }

        this.resp = this.cliente.getNome() + " logado";
        return this.resp;

    }

    public String register() {
        if (autenticacaoBean.isLoggedIn()) {
            cliente = autenticacaoBean.getUsuarioLogado();


            cliente.setDataAlteracao(new Date());
            cliente.setNome(cliente.getNome());

            return "erro";
        } else {

            String senha = cliente.getSenha();

            cliente.setSenha(StringCipher.convertStringToMd5(senha));
            cliente.setDataCadastro(new Date());


            //cliente.setDataAlteracao(new Date());
        }

        clienteRepository.create(cliente);



        return "inserted";
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getResp() {
        return this.resp;
    }


    public List<String> getEstado() {
        List<String> estado = new ArrayList<>();
        estado.add("Acre (AC)");
        estado.add("Alagoas (AL)");
        estado.add("Amapá (AP)");
        estado.add("Amazonas (AM)");
        estado.add("Bahia (BA)");
        estado.add("Ceará (CE)");
        estado.add("Distrito Federal (DF)");
        estado.add("Espírito Santo (ES)");
        estado.add("Goiás (GO)");
        estado.add("Maranhão (MA)");
        estado.add("Mato Grosso (MT)");
        estado.add("Mato Grosso do Sul (MS)");
        estado.add("Minas Gerais (MG)");
        estado.add("Pará (PA)");
        estado.add("Paraíba (PB)");
        estado.add("Paraná (PR)");
        estado.add("Pernambuco (PE)");
        estado.add("Piauí (PI)");
        estado.add("Rio de Janeiro (RJ)");
        estado.add("Rio Grande do Norte (RN)");
        estado.add("Rio Grande do Sul (RS)");
        estado.add("Rondônia (RO)");
        estado.add("Roraima (RR)");
        estado.add("Santa Catarina (SC)");
        estado.add("São Paulo (SP)");
        estado.add("Sergipe (SE)");
        estado.add("Tocantins (TO)                                                                                                                                                                                                                                                                                                                                                                                                                                                            ");
        return estado;
    }
}

