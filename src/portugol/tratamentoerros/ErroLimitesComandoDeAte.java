/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.tratamentoerros;

import portugol.arvoresintatica.NoComandoDeAte;

/**
 *
 * @author Kennedy
 */
public class ErroLimitesComandoDeAte extends Exception {
    public ErroLimitesComandoDeAte(NoComandoDeAte comandoDeAte){
            super (" O  limite inferior não pode ser maior que o limite inferior (linha: "+
                   comandoDeAte.obterLimiteFinal().obterNumeroLinha()+")");
    }
}
