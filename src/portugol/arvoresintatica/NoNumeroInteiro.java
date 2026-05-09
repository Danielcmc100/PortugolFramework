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
public class NoNumeroInteiro extends NoExpressao {
    private int valor;

    public NoNumeroInteiro(int valor, int numeroLinha){
        super(numeroLinha);
        this.valor = valor;
    }

    public TipoValor obterTipoValorExpressao(){
        return TipoValor.INTEIRO;
    }

    public int obterValor(){
        return valor;
    }

    public TipoExpressao obterTipo(){
        return TipoExpressao.NUMERO_INTEIRO;
    }


}
