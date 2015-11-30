package comicstore.utils.conversores;

import javax.faces.convert.Converter;

/**
 * Created by wagner on 28/11/15.
 */
public class TelefoneConverter{

    public String TelefoneConverter(String telefone){
        telefone=telefone.replace("(","");
        telefone=telefone.replace(")","");
        telefone=telefone.replace("-","");
        String tel;
        try {
            if (telefone.length() < 12)
                tel = "(" + telefone.substring(0, 2) + ")" + telefone.substring(2, 7) + "-" + telefone.substring(7, 11);
            else
                tel = "(" + telefone.substring(0, 2) + ")" + telefone.substring(2, 8) + "-" + telefone.substring(8, 12);
            return tel;
        }catch (Exception e){
            return telefone;
        }
    }
}
