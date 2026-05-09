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
public class NoNumeroReal  extends NoExpressao {
    private float valor;

    public NoNumeroReal(float valor, int numeroLinha){
        super(numeroLinha);
        this.valor = valor;
    }

    public TipoValor obterTipoValorExpressao(){
        return TipoValor.REAL;
    }

    public float obterValor(){
        return valor;
    }

    public TipoExpressao obterTipo(){
        return TipoExpressao.NUMERO_REAL;
    }

}
