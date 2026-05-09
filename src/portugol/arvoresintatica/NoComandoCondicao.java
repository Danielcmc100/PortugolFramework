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
public class NoComandoCondicao extends NoComando {
    private NoExpressaoRelacional expressaoRelacional;
    private NoBlocoComandos blocoComandos;
    private NoComando comandoSenao;
    
    public NoComandoCondicao(NoExpressaoRelacional expressaoRelacional, 
                             NoBlocoComandos blocoComandos, 
                             NoComando comandoSenao, 
                             int numeroLinha){
        super(numeroLinha);
        this.expressaoRelacional = expressaoRelacional;
        this.blocoComandos = blocoComandos;
        this.comandoSenao = comandoSenao;
    }

    public NoExpressaoRelacional obterExpressaoRelacional(){
        return expressaoRelacional;
    }
    public NoBlocoComandos obterBlocoComandos(){
        return blocoComandos;
    }

    public NoComando obterComandoSenao(){
        return comandoSenao;
    }

    public NomeComando obterNome(){
        return NomeComando.CONDICAO;
    }



}
