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
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by wagner on 24/11/15.
 */

@Named
@RequestScoped
public class QuadrinhoBean implements Serializable {


    @EJB
    private QuadrinhoRepository quadrinhoRepository;

    private UploadedFile uploadedImage;

    public UploadedFile getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(UploadedFile uploadedImage) {
        this.uploadedImage = uploadedImage;
    }

    private Quadrinho quadrinho;

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

    public String insere(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

        //String realPath = context.getResourcePaths("");
        String realPath = context.getRealPath("/web/");
        System.out.println(realPath);







        //quadrinhoRepository.create(quadrinho);


        return "cadastrado";
    }


}
