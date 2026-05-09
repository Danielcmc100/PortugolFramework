/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.utilitarios;

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
import portugol.arvoresintatica.NoBlocoComandos;
import portugol.arvoresintatica.NoNumeroInteiro;
import portugol.arvoresintatica.NoNumeroReal;
import portugol.arvoresintatica.NoPrograma;
import portugol.arvoresintatica.NoCadeiaCaracteres;
import portugol.arvoresintatica.NoSintatico;
import portugol.arvoresintatica.NomeComando;
import portugol.compilador.TabelaSimbolos;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import portugol.lexico.DadosIdentificador;
import portugol.lexico.Lexema;

/**
 *
 * @author Kennedy
 */
public class ImpressaoArvoreSintatica {

    private TabelaSimbolos tabelaSimbolos;

    private DefaultListModel listaSaida;

    public ImpressaoArvoreSintatica(TabelaSimbolos tabelaSimbolos, DefaultListModel listaSaida){
        this.tabelaSimbolos = tabelaSimbolos;
        this.listaSaida = listaSaida;
    }

    public void imprimir(String saida){
        if (listaSaida!= null){
            listaSaida.addElement(saida);
        } else {
            System.out.println(saida);
        }
    }

    public String obterExpressao (NoExpressao simbolo){
        String operando = "";
        switch (simbolo.obterTipo()){
            case NUMERO_INTEIRO:
                operando = String.valueOf(((NoNumeroInteiro)simbolo).obterValor());
                break;
            case NUMERO_REAL:
                operando = String.valueOf(((NoNumeroReal)simbolo).obterValor());
                break;
            case EXPRESSAO_ARITMETICA:
                operando = obterOperacaoAritmetica((NoExpressaoAritmetica)simbolo);
                break;
            case IDENTIFICADOR:
                operando = String.valueOf(((NoIdentificador)simbolo).obterLexema());
                break;
            case CADEIA_CARACTERES:
                operando = String.valueOf(((NoCadeiaCaracteres)simbolo).obterValor());
        }
        return operando;
    }

    public String obterOperacaoAritmetica(NoExpressaoAritmetica operacaoAritmetica){

        String resultado = "("+obterExpressao(operacaoAritmetica.obterOperandoEsquerdo());

        switch (operacaoAritmetica.obterCodigoOperacao()){
            case ADICAO: resultado += " + "; break;
            case SUBTRACAO: resultado += " - "; break;
            case MULTIPLICACAO: resultado += " * "; break;
            case DIVISAO: resultado += " / "; break;
        }

        resultado += obterExpressao(operacaoAritmetica.obterOperandoDireito())+")";
        return resultado;
    }

    public void lerComandoAtribuicao(NoComandoAtribuicao comandoAtribuicao, String espaco){
       NoIdentificador identificador = comandoAtribuicao.obterIdentificador();
       imprimir(espaco+"  "+
                identificador.obterLexema() + " "+
                Lexema.ATRIBUICAO + " "+
                obterExpressao(comandoAtribuicao.obterExpressao())+
                Lexema.FIM_COMANDO);
    }

    public void escreverComandoLer(NoComandoLer comandoLer, String espaco){
        imprimir(espaco+"  "+Lexema.LER +" "+
                 comandoLer.obterIdentificador().obterLexema()+
                 Lexema.FIM_COMANDO);
    }

    public void lerComandoEscrever(NoComandoEscrever comandoEscrever, String espaco){
        String comando = Lexema.ESCREVER + " ";
        NoSintatico operando = comandoEscrever.obterOperando();
        if (operando instanceof NoIdentificador){
            comando += ((NoIdentificador)operando).obterLexema();
        } else if (operando instanceof NoCadeiaCaracteres){
            comando += '"'+((NoCadeiaCaracteres)operando).obterValor()+'"';
        } else if (operando instanceof NoNumeroInteiro){
            comando += ((NoNumeroInteiro)operando).obterValor();
        } else if (operando instanceof NoNumeroReal){
            comando += ((NoNumeroReal)operando).obterValor();
        }
        comando+=Lexema.FIM_COMANDO;
        imprimir(espaco+"  "+comando);
    }

    public void lerComandoDeAte(NoComandoDeAte comandoDeAte, String espaco){
        imprimir(espaco+
                 Lexema.DE +" "+
                 comandoDeAte.obterIdentificador().obterLexema()+" "+
                 Lexema.ATRIBUICAO + " "+
                 comandoDeAte.obterLimiteInicial().obterValor() + " "+
                 Lexema.ATE + " "+
                 comandoDeAte.obterLimiteFinal().obterValor()+" "+
                 Lexema.FACA);
        escreverBlocoComandos(comandoDeAte.obterBlocoComandos(), espaco+"  ");
    }
    

    public String obterExpressaoLogica(NoExpressaoRelacional expressaoLogica){
        return obterExpressao(expressaoLogica.obterOperandoEsquerdo())+" "+
               Lexema.obterLexemaRelacao(expressaoLogica.obterRelacao())+" "+
               obterExpressao(expressaoLogica.obterOperandoDireito());
    }
    
    public void lerComandoEnquantoFaca(NoComandoEnquantoFaca enquantoFaca, String espaco){
        imprimir(espaco+
                 Lexema.ENQUANTO +" "+
                 Lexema.ABRE_PARENTESES+" "+
                 obterExpressaoLogica(enquantoFaca.obterExpressaoRelacional())+" "+
                 Lexema.FECHA_PARENTESES+" "+
                 Lexema.FACA);
        escreverBlocoComandos(enquantoFaca.obterListaComandos(),espaco+"  ");
    }

    public void escreverComandoCondicao(NoComandoCondicao comandoCondicao, String espaco){
        imprimir(espaco+"  "+
                 Lexema.SE+" "+
                 Lexema.ABRE_PARENTESES+
                 obterExpressaoLogica(comandoCondicao.obterExpressaoRelacional())+
                 Lexema.FECHA_PARENTESES+" "+
                 Lexema.ENTAO);
        escreverBlocoComandos(comandoCondicao.obterBlocoComandos(),espaco+"    ");
        if (comandoCondicao.obterComandoSenao()!= null){
            imprimir(espaco+Lexema.SENAO);
            if (comandoCondicao.obterComandoSenao().obterNome() == NomeComando.CONDICAO){
               escreverComandoCondicao((NoComandoCondicao)comandoCondicao.obterComandoSenao(), espaco+"  ");
            } else {
              escreverBlocoComandos((NoBlocoComandos)comandoCondicao.obterComandoSenao(), espaco+"    ");
           }

        }
        imprimir(espaco+Lexema.FIM_COMANDO);
    }

    public void escreverBlocoComandos(NoBlocoComandos listaComandos, String espaco){

        imprimir(espaco+Lexema.INICIO);

        Iterator it = listaComandos.obterOperacoes().iterator();
        while (it.hasNext()){
            NoComando comando = (NoComando)it.next();
            escreverComando(comando, espaco+"  ");
        }
        imprimir(espaco+Lexema.FIM);
    }

    public void escreverComando(NoComando comando, String espaco){
        switch(comando.obterNome()){
            case LER:
                escreverComandoLer((NoComandoLer)comando, espaco+"  ");
                break;
            case ESCREVER:
                lerComandoEscrever((NoComandoEscrever)comando, espaco+"  ");
                break;
            case LACO_DE_ATE:
                lerComandoDeAte((NoComandoDeAte)comando, espaco+"  ");
                break;
            case LACO_ENQUANTO:
                lerComandoEnquantoFaca((NoComandoEnquantoFaca)comando, espaco + "  ");
                break;
            case BLOCO_COMANDOS:
                escreverBlocoComandos((NoBlocoComandos)comando, espaco + "  ");
                break;
            case ATRIBUICAO:
                lerComandoAtribuicao((NoComandoAtribuicao)comando, espaco + "  ");
                break;
            case CONDICAO:
                escreverComandoCondicao((NoComandoCondicao)comando, espaco + "  ");

        }
    }

    public void executar(NoPrograma programa){
        if (listaSaida !=  null){
            listaSaida.clear();
        } else {
            System.out.println("---------------------------------------------");
        }
        imprimir("  "+Lexema.PROGRAMA +" "+programa.obterIdentificador().obterLexema());

        Iterator<DadosIdentificador> itIdentificadores = tabelaSimbolos.obterDadosIdentificadores().iterator();
//        while (itIdentificadores.hasNext()){
//            DadosIdentificador identificador = itIdentificadores.next();
//            if (identificador.obterTipoIdentificador() != null)
//                imprimir(identificador.obterLexema()+" "+
//                                   Lexema.DOIS_PONTOS+" "+
//                                   identificador.obterTipoIdentificador().obterLexema()+
//                                   Lexema.FIM_COMANDO);
//        }

        imprimir("  "+Lexema.INICIO);
        Iterator it = programa.obterListaComandos().obterOperacoes().iterator();
        while (it.hasNext()){
            NoComando operacao = (NoComando)it.next();
            escreverComando(operacao,"");
        }
        imprimir("  "+Lexema.FIM+Lexema.FIM_COMANDO);
    }

}
