/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.compilador;

import portugol.arvoresintatica.NoPrograma;
import java.util.ArrayList;
import portugol.intermediario.Instrucao;

/**
 *
 * @author Chessman Kennedy Faria Corrêa
 *
 * A tradutor realiza todas as tarefas relacionadas à fase de síntese do
 * compilador, sou seja, análise léxica, análise sintática, análise semântica,
 * e geração de código intermediário.
 */
public class Tradutor {

    private AnalisadorSintatico analisadorSintatico;
    private AnalisadorSemantico analisadorSemantico;
    private GeradorCodigoIntermediario geradorCodigoIntermediario;

    public Tradutor (TabelaSimbolos tabelaSimbolos,TratadorErro tratadorErro){
        analisadorSintatico = new AnalisadorSintatico(tabelaSimbolos,tratadorErro);
        analisadorSemantico = new AnalisadorSemantico(tabelaSimbolos,tratadorErro);
        geradorCodigoIntermediario = new GeradorCodigoIntermediario();//(tabelaSimbolos);
    }

    public ArrayList<Instrucao> executar(String codigo) throws Exception{
        NoPrograma programa = analisadorSintatico.executar(codigo);
        //analisadorSemantico.executar(programa.obterListaComandos());
        return geradorCodigoIntermediario.executar(programa.obterListaComandos());
   }
}
