/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.tratamentoerros;

/**
 *
 * @author Kennedy
 */
public class ErroSintatico extends Exception {
    public ErroSintatico(String lexemaEsperado, String lexemaRetornado, int linha){
        super(lexemaEsperado + " esperado, mas "+
              lexemaRetornado+" encontrado na linha"+
              linha);
    }

     public ErroSintatico(){
         super("Erro sintatico");
     }

}
