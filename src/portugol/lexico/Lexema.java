/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.lexico;

import portugol.arvoresintatica.TipoRelacao;
import portugol.arvoresintatica.TipoValor;

/**
 *
 * @author Kennedy
 */
public class Lexema {
    public static final String PROGRAMA = "programa";
    public static final String INICIO = "inicio";
    public static final String FIM = "fim";
    public static final String INTEIRO = "inteiro";
    public static final String REAL = "real";
    public static final String LER = "ler";
    public static final String ESCREVER = "escrever";
    public static final String SE = "se";
    public static final String ENTAO = "entao";
    public static final String SENAO = "senao";
    public static final String RELACAO_MENOR = "<";
    public static final String RELACAO_MENOR_IGUAL = "<=";
    public static final String RELACAO_MAIOR = ">";
    public static final String RELACAO_MAIOR_IGUAL = ">=";
    public static final String RELACAO_IGUAL = "=";
    public static final String RELACAO_DIFERENTE = "<>";
    public static final String ENQUANTO = "enquanto";
    public static final String FACA = "faca";
    public static final String DE = "de";
    public static final String ATE = "ate";
    public static final String ABRE_PARENTESES = "(";
    public static final String FECHA_PARENTESES = ")";
    public static final String ADICAO = "+";
    public static final String SUBTRACAO = "-";
    public static final String MULTIPLICACAO = "*";
    public static final String DIVISAO = "/";
    public static final String DOIS_PONTOS = ":";
    public static final String FIM_COMANDO = ";";
    public static final String ATRIBUICAO = ":=";
    public static final String CADEIA_CARACTERES = "cadeia";

    //As constantes a seguir não representam lexemas. Eles foram declarados
    //apenas para facilitar a exibição dos tipos de tokens identificados a
    //a partir da janela do compilador quando o botao Lexico é pressionado.
    public static final String TIPO_VARIAVEL = "tipo_variavel";
    public static final String IDENTIFICADOR = "identificador";
    public static final String RELACAO = "relação";

        public static String obterLexemaRelacao(TipoRelacao valor) {
           switch (valor){
               case IGUAL : return RELACAO_IGUAL;
               case DIFERENTE : return RELACAO_DIFERENTE;
               case MAIOR :  return RELACAO_MAIOR;
               case MENOR :  return RELACAO_MENOR;
               case MAIOR_IGUAL :  return RELACAO_MAIOR_IGUAL;
               case MENOR_IGUAL :  return RELACAO_MENOR_IGUAL;
           }
           return "";
        }

        public static String obterTipoRelacao(TipoValor tipoValorExpressao){
           switch (tipoValorExpressao){
               case INTEIRO : return INTEIRO;
               case REAL : return REAL;
               case CADEIA_CARACTERES :  return CADEIA_CARACTERES;
           }
           return "";
        }

        public static String obterNomeToken(Token token) {
        switch (token){
            case PROGRAMA:
                return PROGRAMA;
            case INICIO:
                return INICIO;
            case FIM:
                return FIM;
            case LER:
                return LER;
            case ESCREVER:
                return ESCREVER;
            case SE:
                return SE;
            case ENTAO:
                return ENTAO;
            case SENAO:
                return SENAO;
            case ENQUANTO:
                return ENQUANTO;
            case FACA:
                return FACA;
            case DE:
                return DE;
            case ATE:
                return ATE;
            case ABRE_PARENTESES:
                return ABRE_PARENTESES;
            case FECHA_PARENTESES:
                return FECHA_PARENTESES;
            case ADICAO:
                return ADICAO;
            case SUBTRACAO:
                return SUBTRACAO;
            case MULTIPLICACAO:
                return MULTIPLICACAO;
            case DIVISAO:
                return DIVISAO;
            case DOIS_PONTOS:
                return DOIS_PONTOS;
            case FIM_COMANDO:
                return FIM_COMANDO;
            case ATRIBUICAO:
                return ATRIBUICAO;
            case NUMERO_REAL:
                return REAL;
            case NUMERO_INTEIRO:
                return INTEIRO;
            case CADEIA_CARACTERES:
                return CADEIA_CARACTERES;
            case TIPO_VARIAVEL:
                return TIPO_VARIAVEL;
            case IDENTIFICADOR:
                return IDENTIFICADOR;
            case RELACAO :
                return RELACAO;
            default:
                return "NÃO IDENTIFICADO";
        }
    }



}
