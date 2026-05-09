/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.compilador;

import java.util.ArrayList;
import portugol.intermediario.Instrucao;

/**
 *
 * @author Kennedy
 */
public class Compilador {

    private TabelaSimbolos tabelaSimbolos;
    private Tradutor tradutor;
    private TratadorErro tratadorErro;
    private GeradorCodigoAssembly geradorCodigoAssembly;

    public Compilador(){
        super();
        tabelaSimbolos = new TabelaSimbolos();
        tratadorErro = new TratadorErro();
        tradutor = new Tradutor(tabelaSimbolos,tratadorErro);
        geradorCodigoAssembly = new GeradorCodigoAssembly(tabelaSimbolos, null);
    }

    public void compilar(String codigo) throws Exception {
        ArrayList<Instrucao> instrucoes = tradutor.executar(codigo);
        //String codigoAlvo = geradorCodigoAssembly.executar(instrucoes);
    }

}
