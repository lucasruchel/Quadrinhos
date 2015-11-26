package comicstore.autenticacao.beans;



import comicstore.autenticacao.ejbs.ClienteRepository;
import comicstore.autenticacao.entidades.Cliente;
import comicstore.utils.conversores.StringCipher;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wheezy on 24/11/15.
 */

@Named
@SessionScoped
public class AutenticacaoBean implements Serializable{
    @EJB
    ClienteRepository clienteRepository;

    private boolean loggedIn;
    private Cliente usuarioLogado;
    private String email,senha;


    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public Cliente getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Cliente usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Cliente isUsuarioReadyToLogin(String email, String senha) {
        try {
            email = email.toLowerCase().trim();

            Map parameters = new HashMap<String,Object>();
            parameters.put("email",email.trim().toLowerCase());
            parameters.put("senha", StringCipher.convertStringToMd5(senha));

            List retorno = clienteRepository.findWithNamedQuery("Cliente.findByEmailSenha",parameters);


            if (retorno.size() == 1) {
                Cliente userFound = (Cliente) retorno.get(0);

                return userFound;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public String doLogin(){

        //Verifica se o e-mail e senha existem e se o usuario pode logar
        Cliente usuarioFound = isUsuarioReadyToLogin(email, senha);




        //Caso não tenha retornado nenhum usuario, então mostramos um erro
        //e redirecionamos ele para a página login.xhtml
        //para ele realiza-lo novamente
        if (usuarioFound == null){
            FacesContext.getCurrentInstance().validationFailed();

            return "/login/login.xhtml?faces-redirect=true";
        }else{
            //caso tenha retornado um usuario, setamos a variável loggedIn
            //como true e guardamos o usuario encontrado na variável
            //usuarioLogado. Depois de tudo, mandamos o usuário
            //para a página index.xhtml
            loggedIn = true;
            usuarioLogado = usuarioFound;

            this.email = null;
            this.senha = null;

            return "/index.xhtml?faces-redirect=true";
        }
    }
    //Realiza o logout do usuário logado

    public String doLogout(){

        //Setamos a variável usuarioLogado como nulo, ou seja, limpamos
        //os dados do usuário que estava logado e depois setamos a variável
        //loggedIn como false para sinalizar que o usuário não está mais
        //logado
        usuarioLogado = null;
        loggedIn = false;
        //Mostramos um mensagem ao usuário e redirecionamos ele para a         //página de login

        return "/login/login.xhtml?faces-redirect=true";
    }
















}
