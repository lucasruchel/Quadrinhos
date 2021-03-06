package comicstore.compras.beans;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wheezy on 25/11/15.
 */
@Named
@RequestScoped
public class FileUploadBean implements Serializable{
    private UploadedFile uploadedImage;
    private String destination="/data/quadrinhos/";

    private Pattern pattern;
    private Matcher matcher;

    private static final String IMAGE_PATTERN =
            "([^\\s]+(\\.(?i)(jpg|png|gif))$)";


    public UploadedFile getUploadedImage() {
        return uploadedImage;
    }

        public void setUploadedImage(UploadedFile uploadedImage) {
        this.uploadedImage = uploadedImage;
    }

    @PostConstruct
    private void init(){
        pattern = Pattern.compile(IMAGE_PATTERN);
    }

    public boolean validate(final String image){

        matcher = pattern.matcher(image);
        return matcher.matches();

    }

    public String upload(UploadedFile file,String fileName) {
        FacesMessage msg = new FacesMessage("Success! ", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        String extension = FilenameUtils.getExtension(file.getFileName());
        String fullFileName = fileName+"."+extension;

        // Do what you want with the file

        if(!validate(fullFileName))
            return "default.jpg";

        try {
            copyFile(fullFileName, file.getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullFileName;
    }

public void copyFile(String fileName, InputStream in) {

        try {


            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];


            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
