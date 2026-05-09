/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.arvoresintatica;

import portugol.arvoresintatica.NomeComando;
import java.util.ArrayList;

/**
 *
 * @author Kennedy
 */
public class NoBlocoComandos extends NoComando {

    ArrayList<NoComando> comandos;

    public NoBlocoComandos (int numeroLinha){
        super(numeroLinha);
        comandos = new ArrayList<NoComando>();
    }

    public void incluirOperacao(NoComando operacao){
        comandos.add(operacao);
    }

    public ArrayList<NoComando> obterOperacoes(){
        return comandos;
    }

    public NomeComando obterNome(){
        return NomeComando.BLOCO_COMANDOS;
    }


}
