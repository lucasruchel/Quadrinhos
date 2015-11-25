package comicstore.compras.beans;

import comicstore.compras.ejbs.QuadrinhoRepository;
import comicstore.compras.entidades.Quadrinho;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by wagner on 24/11/15.
 */

@Named
@RequestScoped
public class QuadrinhoBean implements Serializable {


    @EJB
    private QuadrinhoRepository quadrinhoRepository;

    @Inject
    private FileUploadBean fileUploadBean;


    private Quadrinho quadrinho;

    private UploadedFile uploadedImage;

@PostConstruct
    private void init(){
        this.quadrinho = new Quadrinho();
    }

    public Quadrinho getQuadrinho() {
        return quadrinho;
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




    public String insere(){

        quadrinhoRepository.create(quadrinho);

        String fileName = fileUploadBean.upload(uploadedImage,String.valueOf(quadrinho.getId()));

        quadrinho.setImagePath(fileName);
        quadrinhoRepository.update(quadrinho);

        return "cadastrado";
    }


}
