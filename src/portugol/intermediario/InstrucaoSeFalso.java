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
public class InstrucaoSeFalso extends Instrucao {
    private NoExpressao expressao;
    private Rotulo rotulo;
    public InstrucaoSeFalso(NoExpressao expressao, Rotulo rotulo){
        this.expressao = expressao;
        this.rotulo = rotulo;
    }

    public NoExpressao obterExpressao(){
        return expressao;
    }

    public Rotulo obterRotulo(){
        return rotulo;
    }
}
