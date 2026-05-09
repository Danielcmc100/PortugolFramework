/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.utilitarios;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import portugol.arvoresintatica.NoCadeiaCaracteres;
import portugol.arvoresintatica.NoExpressao;
import portugol.arvoresintatica.NoIdentificador;
import portugol.arvoresintatica.NoNumeroInteiro;
import portugol.arvoresintatica.NoNumeroReal;
import portugol.intermediario.Instrucao;
import portugol.intermediario.InstrucaoAritmetica;
import portugol.intermediario.InstrucaoEscrever;
import portugol.intermediario.InstrucaoIrPara;
import portugol.intermediario.InstrucaoLer;
import portugol.intermediario.InstrucaoAtribuir;
import portugol.intermediario.InstrucaoRelacional;
import portugol.intermediario.InstrucaoSeFalso;
import portugol.intermediario.Rotulo;

/**
 *
 * @author Kennedy
 */
public class ImpressaoCodigoTresEnderecos {

    private DefaultListModel listaSaida;


    public ImpressaoCodigoTresEnderecos(DefaultListModel listaSaida){
        this.listaSaida = listaSaida;
    }

    public void imprimir(String saida){
        if (listaSaida!= null){
            listaSaida.addElement(saida);
        } else {
            System.out.println(saida);
        }
    }

    private void escreverInstrucaoLer(InstrucaoLer instrucaoLer){
      imprimir("  ler "+instrucaoLer.obterIdentificador().obterLexema());
    }
    
    private String obterValorExpressao(NoExpressao expressao){
        switch (expressao.obterTipo()){
            case NUMERO_INTEIRO: 
                return String.valueOf(((NoNumeroInteiro)expressao).obterValor());
            case NUMERO_REAL:
                return String.valueOf(((NoNumeroReal)expressao).obterValor());
            case CADEIA_CARACTERES:
                return ((NoCadeiaCaracteres)expressao).obterValor();
            case IDENTIFICADOR:
                return ((NoIdentificador)expressao).obterLexema();
        }
        return "";
    }
    private void escreverInstrucaoEscrever(InstrucaoEscrever instrucaoEscrever){
        imprimir("  escrever "+obterValorExpressao(instrucaoEscrever.obterOperando()));
    }

    private void escreverInstrucaoAritmetica(InstrucaoAritmetica instrucaoAritmetica){
        String operacao;
        switch (instrucaoAritmetica.obterTipoOperacaoOritmetica()){
            case ADICAO:
                operacao = " + ";
                break;
            case SUBTRACAO:
                operacao = " - ";
                break;
            case MULTIPLICACAO:
                operacao = " * ";
                break;
            case DIVISAO:
                operacao = " / ";
                break;
            default:
                operacao = " INDEFINIDO ";
        }

        imprimir("  "+obterValorExpressao(instrucaoAritmetica.obterOperandoRetorno())+
                 " := "+
                 obterValorExpressao(instrucaoAritmetica.obterOperandoEsquerdo())+
                 operacao+
                 obterValorExpressao(instrucaoAritmetica.obterOperandoDireito()));
    }

    private void escreverInstrucaoRelacional(InstrucaoRelacional instrucaoRelacional){
        String operacao;
        switch (instrucaoRelacional.obterTipoOperacao()){
            case DIFERENTE:
                operacao = " <> ";
                break;
            case IGUAL:
                operacao = " = ";
                break;
            case MAIOR:
                operacao = " > ";
                break;
            case MENOR:
                operacao = " < ";
                break;
            case MENOR_IGUAL:
                operacao = " <= ";
                break;
            case MAIOR_IGUAL:
                operacao = " >= ";
                break;
            default:
                operacao = " INDEFINIDO ";
        }

        imprimir("  "+obterValorExpressao(instrucaoRelacional.obterOperandoRetorno())+
                 " := "+
                 obterValorExpressao(instrucaoRelacional.obterOperandoEsquerdo())+
                 operacao+
                 obterValorExpressao(instrucaoRelacional.obterOperandoDireito()));
    }

    private void escreverRotulo(Rotulo rotulo){
        imprimir("rotulo R"+rotulo.obterNumeroRotulo());
    }

    private void escreverInstrucaoIrPara(InstrucaoIrPara instrucaoIrPara){
        imprimir("  ir_para R"+instrucaoIrPara.obterRotulo().obterNumeroRotulo());
    }

    private void escreverInstrucaoMover(InstrucaoAtribuir instrucaoMover){
        imprimir("  "+obterValorExpressao(instrucaoMover.obterDestino())+
                " := "+
                obterValorExpressao(instrucaoMover.obterOrigem()));
    }

    private void escreverInstrucaoSeFalso(InstrucaoSeFalso instrucaoSeFalso){
        imprimir("  se_falso "+
                 obterValorExpressao(instrucaoSeFalso.obterExpressao())+
                 " ir_para R"+instrucaoSeFalso.obterRotulo().obterNumeroRotulo());
    }


    public void executar(ArrayList<Instrucao> instrucoes){
        Iterator it = instrucoes.iterator();
        while (it.hasNext()){
            Instrucao instrucao = (Instrucao)it.next();
            if (instrucao instanceof InstrucaoLer){
                escreverInstrucaoLer((InstrucaoLer)instrucao);
            } else if (instrucao instanceof InstrucaoEscrever){
                escreverInstrucaoEscrever((InstrucaoEscrever)instrucao);
            } else if (instrucao instanceof InstrucaoAritmetica){
                escreverInstrucaoAritmetica((InstrucaoAritmetica)instrucao);
            } else if (instrucao instanceof InstrucaoRelacional){
                escreverInstrucaoRelacional((InstrucaoRelacional)instrucao);
            } else if (instrucao instanceof InstrucaoIrPara){
                escreverInstrucaoIrPara((InstrucaoIrPara)instrucao);
            } else if (instrucao instanceof InstrucaoSeFalso){
                escreverInstrucaoSeFalso((InstrucaoSeFalso)instrucao);
            } else if (instrucao instanceof InstrucaoAtribuir){
                escreverInstrucaoMover((InstrucaoAtribuir)instrucao);
            }else if (instrucao instanceof Rotulo){
                escreverRotulo((Rotulo)instrucao);
            }
        }

    }
}
