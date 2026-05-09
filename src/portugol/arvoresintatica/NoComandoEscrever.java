/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.arvoresintatica;

import portugol.arvoresintatica.NomeComando;
import portugol.arvoresintatica.NoSintatico;

/**
 *
 * @author Kennedy
 */
public class NoComandoEscrever extends NoComando {

    private NoExpressao operando;

    public NoComandoEscrever(NoExpressao operando, int numeroLinha){
        super(numeroLinha);
        this.operando = operando;
    }

    public NoExpressao obterOperando(){
        return operando;
    }

    public NomeComando obterNome(){
        return NomeComando.ESCREVER;
    }

}
