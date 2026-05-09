/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.tratamentoerros;

/**
 *
 * @author Kennedy
 */
public class ErroExpressaoTextoNaoPermitida extends Exception {
    public ErroExpressaoTextoNaoPermitida(int numeroLinha){
        super("Expressão tipo texto não permitida (linha: "+numeroLinha +")");
    }

}
