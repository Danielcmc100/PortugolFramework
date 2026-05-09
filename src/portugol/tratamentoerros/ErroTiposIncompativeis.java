/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.tratamentoerros;

import portugol.arvoresintatica.TipoValor;
import portugol.lexico.Lexema;

/**
 *
 * @author Kennedy
 */
public class ErroTiposIncompativeis extends Exception {
    public ErroTiposIncompativeis(TipoValor tipoValorExpressaoEsperado,
                                  TipoValor tipoValorExpressao,
                                  int numeroLinha){
        
        super("Tipos incompatíveis. Tipo esperado: " + 
              Lexema.obterTipoRelacao(tipoValorExpressaoEsperado)+
              "Tipo definido: "+
              Lexema.obterTipoRelacao(tipoValorExpressao)+
              "(linha: "+numeroLinha);

    }


}
