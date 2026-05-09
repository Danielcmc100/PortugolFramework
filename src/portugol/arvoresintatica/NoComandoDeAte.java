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

public class NoComandoDeAte extends NoComando {
    private NoIdentificador identificador;
    private NoNumeroInteiro limiteInicial;
    private NoNumeroInteiro limiteFinal;
    private NoBlocoComandos blocoComandos;
    public NoComandoDeAte(NoIdentificador identificador,
                          NoNumeroInteiro limiteInicial,
                          NoNumeroInteiro limiteFinal,
                          NoBlocoComandos blocoComandos,
                          int numeroLinha){
        super(numeroLinha);
        this.identificador = identificador;
        this.limiteInicial = limiteInicial;
        this.limiteFinal = limiteFinal;
        this.blocoComandos = blocoComandos;
    }

    public NoIdentificador obterIdentificador(){
        return identificador;
    }

    public NoNumeroInteiro obterLimiteInicial(){
        return limiteInicial;
    }

    public NoNumeroInteiro obterLimiteFinal(){
        return limiteFinal;
    }

    public NoBlocoComandos obterBlocoComandos(){
        return blocoComandos;
    }

    public NomeComando obterNome(){
        return NomeComando.LACO_DE_ATE;
    }


}
