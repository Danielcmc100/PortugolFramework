/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.arvoresintatica;

import portugol.arvoresintatica.NomeComando;
import portugol.lexico.DadosIdentificador;

/**
 *
 * @author Kennedy
 */
public class NoComandoLer extends NoComando {

    private NoIdentificador identificador;

    public NoComandoLer(NoIdentificador identificador, int numeroLinha){
        super(numeroLinha);
        this.identificador = identificador;
    }

    public NoIdentificador obterIdentificador(){
        return identificador;
    }

    public NomeComando obterNome(){
        return NomeComando.LER;
    }

    

}
