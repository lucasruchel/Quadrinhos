package beans;

import ejbs.EjbCliente;
import entidades.Cliente;
import modelo.Cpf;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.Date;

/**
 * Created by wagner on 18/11/15.
 */
@Named
@RequestScoped
public class BeanCliente {

    @EJB
    EjbCliente ejbCliente;


    Cliente cliente;

    boolean logado=false;
    String resp="";

    public String login(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String email = ec.getRequestParameterMap().get("j_login:email");
        String senha = ec.getRequestParameterMap().get("j_login:senha");


        this.cliente=ejbCliente.findByEmailSenha(email,senha);


        if(this.cliente==null){
            logado=false;
            this.resp="login não encontrado";
            return this.resp;
        }
        logado=true;
        this.resp=this.cliente.getNome()+" logado";
        return this.resp;

    }

    public String insere(){


        Cliente cliente;

        if(logado){
            cliente = ejbCliente.find(this.cliente.getId_cliente());


            cliente.setDataAlteracao(new Date());
            cliente.setNome(cliente.getNome());
        }else {
            cliente= new Cliente();

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String nome = ec.getRequestParameterMap().get("j_register:nome");
            String email = ec.getRequestParameterMap().get("j_register:email");
            String senha = ec.getRequestParameterMap().get("j_register:pass");
            String cpf = ec.getRequestParameterMap().get("j_register:cpf");

            System.out.println(ec.getRequestParameterMap().get("j_register:cpf"));
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setSenha(senha);
            cliente.setDataCadastro(new Date());
            cliente.setCpf(cpf);
            //cliente.setDataAlteracao(new Date());
        }

        ejbCliente.persist(cliente);

        return "inserted";
    }
    public void setResp(String resp){
        this.resp=resp;
    }
    public String getResp(){
        return this.resp;
    }
}
