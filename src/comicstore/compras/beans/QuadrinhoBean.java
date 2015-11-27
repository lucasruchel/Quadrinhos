package comicstore.compras.beans;

import comicstore.compras.ejbs.QuadrinhoRepository;
import comicstore.compras.entidades.Quadrinho;
import comicstore.compras.entidades.QuadrinhoEstado;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Named
@SessionScoped
public class QuadrinhoBean implements Serializable {


    @EJB
    private QuadrinhoRepository quadrinhoRepository;

    @Inject
    private FileUploadBean fileUploadBean;


    private Quadrinho quadrinho;

    private UploadedFile uploadedImage;

    private String genero;

    @PostConstruct
    private void init(){

        this.quadrinho = new Quadrinho();
    }

    public Quadrinho getQuadrinho() {
        return this.quadrinho;
    }

    public void setQuadrinho(Quadrinho quadrinho) {
        this.quadrinho = quadrinho;
    }


    public UploadedFile getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(UploadedFile uploadedImage) {
        this.uploadedImage = uploadedImage;
    }


    public List<String> getFaixaEtaria(){
        List<String> faixasEtarias = new ArrayList<String>();

        faixasEtarias.add("Livre");
        faixasEtarias.add("6+");
        faixasEtarias.add("10+");
        faixasEtarias.add("12+");
        faixasEtarias.add("14+");
        faixasEtarias.add("16+");
        faixasEtarias.add("18+");
        faixasEtarias.add("65+");

        return faixasEtarias;
    }


    public List<QuadrinhoEstado> getEstados(){
        return Arrays.asList(QuadrinhoEstado.values());
    }

    public String insere(){
        if(quadrinho.getValorCompra()>quadrinho.getValorVenda()){
            FacesContext.getCurrentInstance().addMessage("j_quadrinho:valorVenda", new FacesMessage("Valor de Venda não Pode ser menor que o de compra!"));
            return null;
        }

        if(quadrinho.getId() == 0)
            quadrinhoRepository.create(quadrinho);

        String fileName = fileUploadBean.upload(uploadedImage,String.valueOf(quadrinho.getId()));

        quadrinho.setImagePath(fileName);
        quadrinhoRepository.update(quadrinho);

        quadrinho = new Quadrinho();

        return "cadastrado";
    }
    public String delete (Quadrinho quadrinho){
        this.quadrinhoRepository.delete(Quadrinho.class,quadrinho.getId());
        return "deletado";
    }

    public String edit (Quadrinho quadrinho){
        this.quadrinho = quadrinho;

        return "editar";
    }

    public List<Quadrinho> buscaQuadrinho(){
        return quadrinhoRepository.findWithNamedQuery("Quadrinho.findByQuadrinho");

    }

}
