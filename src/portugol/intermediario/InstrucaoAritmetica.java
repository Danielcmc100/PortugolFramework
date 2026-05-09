/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.intermediario;

import portugol.arvoresintatica.NoExpressao;
import portugol.arvoresintatica.NoIdentificador;
import portugol.arvoresintatica.TipoOperacaoAritmetica;

/**
 *
 * @author Kennedy
 */
public class InstrucaoAritmetica extends Instrucao {

    private TipoOperacaoAritmetica tipoOperacaoAritmetica;
    private NoIdentificador operandoRetorno;
    private NoExpressao operandoEsquerdo;
    private NoExpressao operandoDireito;

    public InstrucaoAritmetica(TipoOperacaoAritmetica tipoOperacaoAritmetica,
                                       NoIdentificador operandoRetorno,
                                       NoExpressao operandoEsquerdo,
                                       NoExpressao operandoDireito){

        this.tipoOperacaoAritmetica = tipoOperacaoAritmetica;
        this.operandoRetorno = operandoRetorno;
        this.operandoEsquerdo = operandoEsquerdo;
        this.operandoDireito = operandoDireito;
    }

    public TipoOperacaoAritmetica obterTipoOperacaoOritmetica(){
        return tipoOperacaoAritmetica;
    }

    public NoIdentificador obterOperandoRetorno(){
        return operandoRetorno;
    }

    public NoExpressao obterOperandoEsquerdo(){
        return operandoEsquerdo;
    }

    public NoExpressao obterOperandoDireito(){
        return operandoDireito;
    }

}
