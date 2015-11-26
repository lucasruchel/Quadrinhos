package comicstore.compras.beans;

import comicstore.compras.ejbs.QuadrinhoRepository;
import comicstore.compras.entidades.Quadrinho;
import comicstore.compras.entidades.QuadrinhoEstado;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Named
@RequestScoped
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

        quadrinhoRepository.create(quadrinho);

        String fileName = fileUploadBean.upload(uploadedImage,String.valueOf(quadrinho.getId()));

        quadrinho.setImagePath(fileName);
        quadrinhoRepository.update(quadrinho);

        return "cadastrado";
    }

    public List<Quadrinho> buscaQuadrinho(){
        return quadrinhoRepository.findWithNamedQuery("Quadrinho.findByQuadrinho");

    }

}
