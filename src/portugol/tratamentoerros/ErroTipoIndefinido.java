/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.tratamentoerros;

import portugol.lexico.DadosIdentificador;


/**
 *
 * @author Kennedy
 */
public class ErroTipoIndefinido extends Exception {
    public ErroTipoIndefinido(DadosIdentificador identificador) {
            super ("Identificador "+identificador.obterNome()+
                   " sem tipo definido (linha");//+
                   //identificador.obterNumeroLinha()+")");
    }

}
