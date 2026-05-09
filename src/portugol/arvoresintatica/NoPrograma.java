/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.arvoresintatica;

import portugol.arvoresintatica.NoSintatico;

/**
 *
 * @author Kennedy
 */
public class NoPrograma extends NoSintatico{

    private NoBlocoComandos blocoComandos;
    private NoIdentificador identificador;

    public NoPrograma (NoIdentificador identificador, 
                       NoBlocoComandos blocoComandos){
        super(1);
        this.identificador = identificador;
        this.blocoComandos = blocoComandos;
    }

    public NoBlocoComandos obterListaComandos(){
        return blocoComandos;
    }

    public NoIdentificador obterIdentificador(){
        return identificador;
    }
}
