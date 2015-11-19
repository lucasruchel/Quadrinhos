/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.InputMismatchException;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author wagner
 */
@FacesConverter(forClass=Cpf.class)
public class CpfConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String CPF) {

        CPF= CPF.trim();
        CPF=CPF.replace(".","");
        CPF=CPF.replace("-","");
        //\sé espaco sem ser branco
        // + uma ou mais, quantificador
        String padrao="[0-9.-]+";
        FacesMessage mensagem = new FacesMessage ("Número inválido de CPF") ;
        if(!Pattern.matches(padrao,CPF)){
            // FacesMessage mensagem = new FacesMessage ("Número inválido de CPF") ;
            mensagem.setSeverity (FacesMessage.SEVERITY_ERROR) ;
            throw new ConverterException (mensagem) ;
        }
        String campos [] = CPF.split(".+-");
        Cpf c =new Cpf();



        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))

            throw new ConverterException (mensagem) ;

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))){
                c.setDigitos1(campos[0]);
                return c;
            }
            else throw new ConverterException (mensagem) ;
        } catch (InputMismatchException erro) {
            throw new ConverterException (mensagem) ;
        }

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Cpf cpf= (Cpf)o;
        String valor = "";
        String teste = cpf.getDigitos1().valueOf(cpf.getDigitos1());
        for (int i = 1;i<=teste.length();i++) {
            if (!equals(teste.charAt(i-1))) {
                valor += teste.charAt(i-1);
                if (valor.length()==3|valor.length()==7) {
                    valor += ".";
                }
                if (valor.length()==11) {
                    valor += "-";
                }
            }
        }
        cpf.setDigitos1(valor);
        return cpf.getDigitos1();
    }

}
