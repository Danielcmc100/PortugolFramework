/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.arvoresintatica;

import portugol.arvoresintatica.NomeComando;

/**
 *
 * @author Kennedy
 */
public class NoComandoEnquantoFaca extends NoComando {

    private NoExpressaoRelacional expressaoRelacional;
    private NoBlocoComandos blocoComandos;

    public NoComandoEnquantoFaca(NoExpressaoRelacional expressaoRelacional, 
                                 NoBlocoComandos blocoComandos, 
                                 int numeroLinha){
        super(numeroLinha);
        this.expressaoRelacional = expressaoRelacional;
        this.blocoComandos = blocoComandos;
    }

    public NoExpressaoRelacional obterExpressaoRelacional(){
        return expressaoRelacional;
    }

    public NoBlocoComandos obterListaComandos(){
        return blocoComandos;
    }

    public NomeComando obterNome(){
        return NomeComando.LACO_ENQUANTO;
    }


}
