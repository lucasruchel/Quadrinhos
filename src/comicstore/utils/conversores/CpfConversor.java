package comicstore.utils.conversores;

/**
 * Created by wagner on 28/11/15.
 */
public class CpfConversor {
    public CpfConversor() {
    }
    public String converterCPF(String CPF){
        String cpf="";
        CPF=CPF.replace(".", "");
        CPF=CPF.replace("-", "");
        cpf=(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    return cpf;
    }
}
