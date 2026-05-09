/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.compilador;

import portugol.arvoresintatica.NoBlocoComandos;
import java.util.ArrayList;
import java.util.Iterator;
import portugol.arvoresintatica.NoComando;
import portugol.arvoresintatica.NoComandoAtribuicao;
import portugol.arvoresintatica.NoComandoCondicao;
import portugol.arvoresintatica.NoComandoDeAte;
import portugol.arvoresintatica.NoComandoEnquantoFaca;
import portugol.arvoresintatica.NoComandoEscrever;
import portugol.arvoresintatica.NoComandoLer;
import portugol.arvoresintatica.NoExpressao;
import portugol.arvoresintatica.NoExpressaoAritmetica;
import portugol.arvoresintatica.NoExpressaoRelacional;
import portugol.arvoresintatica.NoIdentificador;
import portugol.arvoresintatica.NoNumeroInteiro;
import portugol.arvoresintatica.NomeComando;
import portugol.arvoresintatica.TipoOperacaoAritmetica;
import portugol.intermediario.Instrucao;
import portugol.intermediario.InstrucaoEscrever;
import portugol.intermediario.InstrucaoIrPara;
import portugol.intermediario.InstrucaoLer;
import portugol.intermediario.InstrucaoAritmetica;
import portugol.intermediario.InstrucaoAtribuir;
import portugol.intermediario.InstrucaoRelacional;
import portugol.intermediario.InstrucaoSeFalso;
import portugol.intermediario.Rotulo;
import portugol.lexico.DadosIdentificador;
import portugol.arvoresintatica.TipoRelacao;

/**
 *
 * @author Kennedy
 */
public class GeradorCodigoIntermediario {

    private ArrayList<Instrucao> instrucoes;
    private int numeroRotulo = 0;
    private int numeroVariavelTemporaria = 0;


    public GeradorCodigoIntermediario(){
        instrucoes = new ArrayList<Instrucao>();
    }

    public  ArrayList<Instrucao> executar (NoBlocoComandos listaComandos) throws Exception {
        traduzirComandos(listaComandos);
        return instrucoes;
    }

    private Rotulo criarRotulo(){
        numeroRotulo++;
        return new Rotulo(numeroRotulo);
    }

    private NoIdentificador criarVariavelTemporaria(){
        numeroVariavelTemporaria++;
        DadosIdentificador dadosIdentificador = new DadosIdentificador("temp_"+numeroVariavelTemporaria);

        return new NoIdentificador(dadosIdentificador, 0);
    }

    public void traduzirComandos(NoBlocoComandos listaComandos) throws Exception {
        Iterator it = listaComandos.obterOperacoes().iterator();
        while (it.hasNext()){
            NoComando comando = (NoComando)it.next();
            switch(comando.obterNome()){
               case LER:
                    traduzirComandoLer((NoComandoLer)comando);
                    break;
               case ESCREVER:
                    traduzirComandoEscrever((NoComandoEscrever)comando);
                    break;
                case LACO_DE_ATE:
                    traduzirComandoDeAte((NoComandoDeAte)comando);
                    break;
                case LACO_ENQUANTO:
                    traduzirComandoEnquantoFaca((NoComandoEnquantoFaca)comando);
                    break;
                case BLOCO_COMANDOS:
                    traduzirComandos((NoBlocoComandos)comando);
                    break;
                case ATRIBUICAO:
                    traduzirComandoAtribuicao((NoComandoAtribuicao)comando);
                    break;
                case CONDICAO:
                    traduzirComandoCondicao((NoComandoCondicao)comando);
            }
        }
    }
    
    private NoExpressao traduzirExpressao(NoExpressao operando){
        
        switch (operando.obterTipo()){
            case NUMERO_INTEIRO:
            case NUMERO_REAL:
            case IDENTIFICADOR:
                return operando;
            default:
                return traduzirExpressaoAritmetica((NoExpressaoAritmetica)operando);
        }
    }

    private NoIdentificador traduzirExpressaoAritmetica(NoExpressaoAritmetica expressaoAritmetica){
        
        NoExpressao operandoEsquerdo = traduzirExpressao(expressaoAritmetica.obterOperandoEsquerdo());
        NoExpressao operandoDireito  = traduzirExpressao(expressaoAritmetica.obterOperandoDireito());

        /* A tradução de uma expressão aritmética para a forma de três endereços
         * requer a criação de uma variável temporária (representada pela
         * classe NoIdentificador) para armazenar o resultado da operação entre
         * dois operadores. A variável criada é retornada, de forma que
         * possa ser usada em outra expressão aritmética de três endereços.
         */ 
        NoIdentificador identificador = criarVariavelTemporaria();

        InstrucaoAritmetica instrucaoAritmetica = new InstrucaoAritmetica(expressaoAritmetica.obterCodigoOperacao(),
                                                                          identificador,
                                                                          operandoEsquerdo,
                                                                          operandoDireito);
        instrucoes.add(instrucaoAritmetica);

        return identificador;
    }

    private NoExpressao traduzirExpressaoRelacional(NoExpressaoRelacional expressaoRelacional) throws Exception {
        
        NoExpressao operandoEsquerdo = traduzirExpressao(expressaoRelacional.obterOperandoEsquerdo());
        NoExpressao operandoDireito = traduzirExpressao(expressaoRelacional.obterOperandoDireito());

        NoIdentificador operandoResultado = criarVariavelTemporaria();

        InstrucaoRelacional instrucaoRelacional = new InstrucaoRelacional(expressaoRelacional.obterRelacao(),
                                                                          operandoResultado,
                                                                          operandoEsquerdo,
                                                                          operandoDireito);
        instrucoes.add(instrucaoRelacional);
        return operandoResultado;
    }

 

    //Métodos para completar ---------------------------------------------------
    private void traduzirComandoLer(NoComandoLer comandoLer){
        InstrucaoLer instrucaoLer = new InstrucaoLer(comandoLer.obterIdentificador());
        instrucoes.add(instrucaoLer);
    }

    private void traduzirComandoEscrever(NoComandoEscrever comandoEscrever){
        InstrucaoEscrever instrucaoEscrever = new InstrucaoEscrever(comandoEscrever.obterOperando());
        instrucoes.add(instrucaoEscrever);
    }

    private void traduzirComandoCondicao(NoComandoCondicao comandoCondicao) throws Exception {
        //Completar aqui.
        //Usar instrucoes.add() para incluir as instruções.
    }

    private void traduzirComandoEnquantoFaca(NoComandoEnquantoFaca comandoEnquantoFaca) throws Exception{
       
        //Completar aqui.
        //Usar instrucoes.add() para incluir as instruções.

    }

    private void traduzirComandoDeAte(NoComandoDeAte comandoDeAte) throws Exception {
       
        //Completar aqui.
        //Usar instrucoes.add() para incluir as instruções.

    }


    private void traduzirComandoAtribuicao(NoComandoAtribuicao comandoAtribuicao) throws Exception {
        //Completar aqui.
        //Usar instrucoes.add() para incluir as instruções.

    }

    

}
