package validadores;

import modelo.Email;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wagner on 19/11/15.
 */

@FacesValidator(value = "emailValidator")
public class EmailValidador implements Validator {


    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException{
            String email = (String) o;

            String regex ="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
            FacesMessage mensagem = new FacesMessage ("Email não é valido") ;
            if(!Pattern.matches(regex, email)){
                mensagem.setSeverity (FacesMessage.SEVERITY_ERROR) ;
                throw new ValidatorException(mensagem) ;
            }
    }

}
