package beans;

import ejbs.ClienteRepository;
import entidades.Cliente;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 18/11/15.
 */
@Named
@SessionScoped
public class BeanCliente implements Serializable{

    @EJB
    ClienteRepository clienteRepository;

    private String estado;

    Cliente cliente;

    boolean logado = false;
    String resp = "";

    public String login() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String email = ec.getRequestParameterMap().get("j_login:email");
        String senha = ec.getRequestParameterMap().get("j_login:senha");


        this.cliente = clienteRepository.findByEmailSenha(email, senha);


        if (this.cliente == null) {
            logado = false;
            this.resp = "login não encontrado";
            return this.resp;
        }
        logado = true;
        this.resp = this.cliente.getNome() + " logado";
        return this.resp;

    }

    public String insere() {


        Cliente cliente;

        if (logado) {
            cliente = clienteRepository.find(this.cliente.getId_cliente());


            cliente.setDataAlteracao(new Date());
            cliente.setNome(cliente.getNome());
        } else {
            cliente = new Cliente();

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String nome = ec.getRequestParameterMap().get("j_register:nome");
            String email = ec.getRequestParameterMap().get("j_register:email");
            String senha = ec.getRequestParameterMap().get("j_register:pass");
            String cpf = ec.getRequestParameterMap().get("j_register:cpf");
            String telefone = ec.getRequestParameterMap().get("j_register:telefone");
            String cep = ec.getRequestParameterMap().get("j_register:cep");
            String rua = ec.getRequestParameterMap().get("j_register:rua");
            String bairro = ec.getRequestParameterMap().get("j_register:bairro");
            String cidade = ec.getRequestParameterMap().get("j_register:cidade");
            String numero = ec.getRequestParameterMap().get("j_register:numero");
            String estado=ec.getRequestParameterMap().get("j_register:estado");

            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setSenha(senha);
            cliente.setDataCadastro(new Date());
            cliente.setCpf(cpf);
            cliente.setTelefone(telefone);
            cliente.setCep(cep);
            cliente.setRua(rua);
            cliente.setBairro(bairro);
            cliente.setCidade(cidade);
            cliente.setNumero(numero);
            cliente.setEstado(estado);

            //cliente.setDataAlteracao(new Date());
        }

        clienteRepository.inserir(cliente);

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

