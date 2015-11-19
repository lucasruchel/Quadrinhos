package validadores;

import modelo.Cpf;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

/**
 * Created by wagner on 19/11/15.
 */

@FacesValidator(value = "cpfvalidor")
public class CpfValidador implements Validator{
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String CPF = (String) o;

        CPF = CPF.trim();
        CPF = CPF.replace(".", "");
        CPF = CPF.replace("-", "");
        //\sé espaco sem ser branco
        // + uma ou mais, quantificador

        String padrao = "[0-9.-]+";
        FacesMessage mensagem = new FacesMessage("Número inválido de CPF");

        if (!Pattern.matches(padrao, CPF)) {
            // FacesMessage mensagem = new FacesMessage ("Número inválido de CPF") ;
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(mensagem);
        }
        String campos[] = CPF.split(".+-");
        Cpf c = new Cpf();


        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))

            throw new ValidatorException(mensagem);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                c.setDigitos1(campos[0]);

            } else throw new ValidatorException(mensagem);
        } catch (InputMismatchException erro) {
            throw new ValidatorException(mensagem);
        }
    }
}
