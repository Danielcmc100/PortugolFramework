/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.intermediario;

import portugol.arvoresintatica.NoExpressao;
import portugol.arvoresintatica.NoIdentificador;

/**
 *
 * @author Kennedy
 */
public class InstrucaoAtribuir extends Instrucao{

    private NoExpressao origem;
    private NoIdentificador destino;
    
    public InstrucaoAtribuir (NoExpressao origem, NoIdentificador destino){
        this.origem = origem;
        this.destino = destino;
    }

    public NoExpressao obterOrigem(){
        return origem;
    }

    public NoIdentificador obterDestino(){
        return destino;
    }

}
