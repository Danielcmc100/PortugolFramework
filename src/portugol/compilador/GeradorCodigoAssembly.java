/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.compilador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.swing.DefaultListModel;

import portugol.arvoresintatica.NoCadeiaCaracteres;
import portugol.arvoresintatica.NoExpressao;
import portugol.arvoresintatica.NoIdentificador;
import portugol.arvoresintatica.NoNumeroInteiro;
import portugol.arvoresintatica.NoNumeroReal;
import portugol.intermediario.Instrucao;
import portugol.intermediario.InstrucaoAritmetica;
import portugol.intermediario.InstrucaoAtribuir;
import portugol.intermediario.InstrucaoEscrever;
import portugol.intermediario.InstrucaoIrPara;
import portugol.intermediario.InstrucaoLer;
import portugol.intermediario.InstrucaoRelacional;
import portugol.intermediario.InstrucaoSeFalso;
import portugol.intermediario.Rotulo;
import portugol.lexico.Assembly;
import portugol.lexico.DadosIdentificador;

/**
 *
 * @author Kennedy
 */
public class GeradorCodigoAssembly {

    private DefaultListModel listaSaida;

    private String codigoAssembly = "";

    private int numeroRotulo = 0;

    private TabelaSimbolos tabelaSimbolos;

    public GeradorCodigoAssembly(TabelaSimbolos tabelaSimbolos,
            DefaultListModel listaSaida) {
        this.tabelaSimbolos = tabelaSimbolos;
        this.listaSaida = listaSaida;
    }

    private void criarBlocoCodigo(ArrayList<Instrucao> instrucoes) {
        numeroRotulo = 0;
        Iterator it = instrucoes.iterator();
        while (it.hasNext()) {
            Instrucao instrucao = (Instrucao) it.next();
            // Ler
            if (instrucao instanceof InstrucaoLer) {

                escreverInstrucaoLer((InstrucaoLer) instrucao);
                // Escrever
            } else if (instrucao instanceof InstrucaoEscrever) {

                escreverInstrucaoEscrever((InstrucaoEscrever) instrucao);
                // Operação aritmética
            } else if (instrucao instanceof InstrucaoAritmetica) {

                gerarInstrucaoAritmetica((InstrucaoAritmetica) instrucao);

            } else if (instrucao instanceof InstrucaoRelacional) {

                escreverInstrucaoRelacional((InstrucaoRelacional) instrucao);

            } else if (instrucao instanceof InstrucaoIrPara) {

                escreverInstrucaoIrPara((InstrucaoIrPara) instrucao);

            } else if (instrucao instanceof InstrucaoSeFalso) {

                escreverInstrucaoSeFalso((InstrucaoSeFalso) instrucao);

            } else if (instrucao instanceof InstrucaoAtribuir) {

                escreverInstrucaoMover((InstrucaoAtribuir) instrucao);

            } else if (instrucao instanceof Rotulo) {

                gerarRotulo((Rotulo) instrucao);
            }
        }
    }

    private void criarBlocoDados(ArrayList<Instrucao> instrucoes) {
        // Declara, como DWORD, todas as variáveis do programa (tabela de símbolos)
        // e as variáveis temporárias geradas no código intermediário.
        LinkedHashSet<String> nomes = new LinkedHashSet<String>();

        Iterator<DadosIdentificador> itSimbolos = tabelaSimbolos.obterDadosIdentificadores().iterator();
        while (itSimbolos.hasNext()) {
            nomes.add(itSimbolos.next().obterNome());
        }

        Iterator it = instrucoes.iterator();
        while (it.hasNext()) {
            Instrucao instrucao = (Instrucao) it.next();
            if (instrucao instanceof InstrucaoAritmetica) {
                nomes.add(((InstrucaoAritmetica) instrucao).obterOperandoRetorno().obterLexema());
            } else if (instrucao instanceof InstrucaoRelacional) {
                nomes.add(((InstrucaoRelacional) instrucao).obterOperandoRetorno().obterLexema());
            }
        }

        for (String nome : nomes) {
            imprimir(nome + " DWORD ?");
        }
    }

    public void executar(ArrayList<Instrucao> instrucoes) {
        imprimir(".386");// Utiliza isntruções compatíveis com o 80386
        imprimir(".model flat, stdcall"); // Define o modelo de controle de memória
        imprimir("option casemap: none");
        imprimir(" ");
        imprimir("include c:\\masm32\\include\\windows.inc");
        imprimir("include c:\\masm32\\include\\kernel32.inc");
        imprimir("include c:\\masm32\\include\\masm32.inc");
        imprimir(" ");
        imprimir("includelib c:\\masm32\\lib\\kernel32.lib");
        imprimir("includelib c:\\masm32\\lib\\masm32.lib");
        imprimir(" ");

        imprimir(".data");
        criarBlocoDados(instrucoes);
        imprimir(".code");
        imprimir("start:");
        imprimir(" ");
        criarBlocoCodigo(instrucoes);
        imprimir(" ");
        imprimir("invoke ExitProcess, 0");
        imprimir("end start");
    }

    public void imprimir(String saida) {
        codigoAssembly = codigoAssembly + saida + "/n";

        if (listaSaida != null) {
            listaSaida.addElement(saida);
        } else {
            System.out.println(saida);
        }
    }

    private void escreverInstrucaoLer(InstrucaoLer instrucaoLer) {
        imprimir("SO_ler " + instrucaoLer.obterIdentificador().obterLexema());
    }

    private String obterValorExpressao(NoExpressao expressao) {
        switch (expressao.obterTipo()) {
            case NUMERO_INTEIRO:
                return String.valueOf(((NoNumeroInteiro) expressao).obterValor());
            case NUMERO_REAL:
                return String.valueOf(((NoNumeroReal) expressao).obterValor());
            case CADEIA_CARACTERES:
                return ((NoCadeiaCaracteres) expressao).obterValor();
            case IDENTIFICADOR:
                return ((NoIdentificador) expressao).obterLexema();
        }
        return "";
    }

    private void escreverInstrucaoEscrever(InstrucaoEscrever instrucaoEscrever) {
        imprimir("invoke StdOut, addr " + obterValorExpressao(instrucaoEscrever.obterOperando()));
    }

    private void gerarRotulo(Rotulo rotulo) {
        imprimir("r" + rotulo.obterNumeroRotulo() + ":");
    }

    private String gerarRotuloInterno() {
        numeroRotulo++;
        return "_r" + String.valueOf(numeroRotulo);
    }

    private void escreverInstrucaoIrPara(InstrucaoIrPara instrucaoIrPara) {
        imprimir("jmp r" + instrucaoIrPara.obterRotulo().obterNumeroRotulo());
    }

    // Métodos a serem completados ----------------------------------------------

    private void gerarInstrucaoAritmetica(InstrucaoAritmetica instrucaoAritmetica) {
        String operacao;
        switch (instrucaoAritmetica.obterTipoOperacaoOritmetica()) {
            case ADICAO:
                operacao = Assembly.ADD;
                break;
            case SUBTRACAO:
                operacao = Assembly.SUB;
                break;
            case MULTIPLICACAO:
                operacao = Assembly.MUL;
                break;
            case DIVISAO:
                operacao = Assembly.DIV;
                break;
            default:
                operacao = "?";
        }

        String esquerdo = obterValorExpressao(instrucaoAritmetica.obterOperandoEsquerdo());
        String direito = obterValorExpressao(instrucaoAritmetica.obterOperandoDireito());
        String retorno = obterValorExpressao(instrucaoAritmetica.obterOperandoRetorno());

        imprimir(Assembly.MOV + " " + Assembly.EAX + ", " + esquerdo);
        if (operacao.equals(Assembly.ADD) || operacao.equals(Assembly.SUB)) {
            // add/sub operam diretamente sobre eax
            imprimir(operacao + " " + Assembly.EAX + ", " + direito);
        } else {
            // mul/div usam eax como operando implícito e um registrador/memória
            imprimir(Assembly.MOV + " ebx, " + direito);
            imprimir(operacao + " ebx");
        }
        imprimir(Assembly.MOV + " " + retorno + ", " + Assembly.EAX);
    }

    private void escreverInstrucaoRelacional(InstrucaoRelacional instrucaoRelacional) {

        String operacao;
        String rotulo1;
        String rotulo2;

        switch (instrucaoRelacional.obterTipoOperacao()) {
            case DIFERENTE:
                operacao = "jne";
                break;
            case IGUAL:
                operacao = "je";
                break;
            case MAIOR:
                operacao = "jg";
                break;
            case MENOR:
                operacao = "jl";
                break;
            case MENOR_IGUAL:
                operacao = "jle";
                break;
            case MAIOR_IGUAL:
                operacao = "jge";
                break;
            default:
                operacao = "?";
        }

        rotulo1 = gerarRotuloInterno();
        rotulo2 = gerarRotuloInterno();

        String esquerdo = obterValorExpressao(instrucaoRelacional.obterOperandoEsquerdo());
        String direito = obterValorExpressao(instrucaoRelacional.obterOperandoDireito());
        String retorno = obterValorExpressao(instrucaoRelacional.obterOperandoRetorno());

        // retorno recebe 1 se relação verdadeira, 0 caso contrário
        imprimir(Assembly.MOV + " " + Assembly.EAX + ", " + esquerdo);
        imprimir("cmp " + Assembly.EAX + ", " + direito);
        imprimir(operacao + " " + rotulo1);
        imprimir(Assembly.MOV + " " + retorno + ", 0");
        imprimir("jmp " + rotulo2);
        imprimir(rotulo1 + ":");
        imprimir(Assembly.MOV + " " + retorno + ", 1");
        imprimir(rotulo2 + ":");
    }

    private void escreverInstrucaoMover(InstrucaoAtribuir instrucaoMover) {
        String origem = obterValorExpressao(instrucaoMover.obterOrigem());
        String destino = obterValorExpressao(instrucaoMover.obterDestino());

        imprimir(Assembly.MOV + " " + Assembly.EAX + ", " + origem);
        imprimir(Assembly.MOV + " " + destino + ", " + Assembly.EAX);
    }

    private void escreverInstrucaoSeFalso(InstrucaoSeFalso instrucaoSeFalso) {
        String expressao = obterValorExpressao(instrucaoSeFalso.obterExpressao());
        int numeroRotuloDestino = instrucaoSeFalso.obterRotulo().obterNumeroRotulo();

        // se_falso expr ir_para R  ->  salta para R quando expr == 0
        imprimir("cmp " + expressao + ", 0");
        imprimir("je r" + numeroRotuloDestino);
    }

}
