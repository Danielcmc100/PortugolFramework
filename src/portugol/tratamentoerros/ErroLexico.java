/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.tratamentoerros;

/**
 *
 * @author Kennedy
 */
public class ErroLexico extends Exception {
    public ErroLexico(String lexemaRetornado, int linha, int coluna){
            super (" O simbolo "+lexemaRetornado+" não pertence à linguagem fonte "+
                   "(linha "+linha+", coluna: "+coluna+")" );
    }
}
