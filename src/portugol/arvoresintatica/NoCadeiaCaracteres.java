/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.arvoresintatica;

import portugol.arvoresintatica.TipoExpressao;

/**
 *
 * @author Kennedy
 */
public class NoCadeiaCaracteres extends NoExpressao{
    
    private String valor;

    public NoCadeiaCaracteres(String valor, int numeroLinha){
        super(numeroLinha);
        this.valor = valor;
    }

    public String obterValor(){
        return valor;
    }

    public TipoValor obterTipoValorExpressao(){
        return TipoValor.CADEIA_CARACTERES;
    }

    public TipoExpressao obterTipo(){
        return TipoExpressao.CADEIA_CARACTERES;
    }

}
