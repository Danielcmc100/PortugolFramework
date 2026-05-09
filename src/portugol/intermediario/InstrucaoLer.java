/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.intermediario;

import portugol.arvoresintatica.NoIdentificador;

/**
 *
 * @author Kennedy
 */
public class InstrucaoLer extends Instrucao {
    private NoIdentificador identificador;

    public InstrucaoLer(NoIdentificador identificador){
        this.identificador = identificador;
    }

    public NoIdentificador obterIdentificador(){
        return identificador;
    }

}
