/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.intermediario;

/**
 *
 * @author Kennedy
 */
public class InstrucaoIrPara extends Instrucao {

    private Rotulo rotulo;

    public InstrucaoIrPara(Rotulo rotulo){
        this.rotulo = rotulo;
    }

    public Rotulo obterRotulo(){
        return rotulo;
    }
}
