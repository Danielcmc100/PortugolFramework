/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.intermediario;

import portugol.arvoresintatica.NoExpressao;

/**
 *
 * @author Kennedy
 */
public class InstrucaoEscrever extends Instrucao {
    private NoExpressao operando;
    public InstrucaoEscrever(NoExpressao operando){
        this.operando = operando;
    }

    public NoExpressao obterOperando(){
        return operando;
    }
}
