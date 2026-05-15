/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.compilador;

import portugol.lexico.DadosIdentificador;
import portugol.lexico.Token;

/**
 *
 * @author Kennedy
 */
public class AnalisadorLexico {

    private char[] vetorCodigo;
    private int posicaoInicial = 0;
    private int posicaoFinal = 0;
    private int numeroLinha = 1;
    private String lexema;

    private TabelaSimbolos tabelaSimbolos;

    public AnalisadorLexico(TabelaSimbolos tabelaSimbolos) {
        this.tabelaSimbolos = tabelaSimbolos;
        this.lexema = "";
    }

    public String obterLexema() {
        return lexema;
    }

    public DadosIdentificador obterDadosIdentificador() {
        return (DadosIdentificador) tabelaSimbolos.obterDadosToken(lexema);
    }

    public void definirCodigo(String codigo) {
        /*
         * O simbolo $ esta sendo usado como indicador de final de codigo. Esta
         * tecnica facilita a varredura do vetor de caracteres.
         */
        vetorCodigo = codigo.concat("$").toCharArray();
        posicaoInicial = 0;
        posicaoFinal = 0;
        numeroLinha = 1;
        tabelaSimbolos.iniciar();

    }

    public int obterNumeroLinha() {
        return numeroLinha;
    }

    public int obterNumeroColuna() {
        return posicaoInicial;
    }

    public boolean temCodigo() {
        return (vetorCodigo[posicaoFinal] != '$');
    }

    /**
     * O método saltarEspacos avança a posicaoFinal para a próxima posição
     * onde existe um caractere considerado como válido pela gramática. É
     * importante notar que quando um caractere de salto de linha é encontrado
     * (\n), o atributo numeroLinha é incrementado.
     */
    public void saltarEspacos() {
        boolean caractereEspaco = (vetorCodigo[posicaoFinal] == ' ') || (vetorCodigo[posicaoFinal] == '\n');
        while ((posicaoFinal < vetorCodigo.length) && caractereEspaco) {

            if (vetorCodigo[posicaoFinal] == '\n') {
                numeroLinha++;
            }

            posicaoFinal++;
            caractereEspaco = (vetorCodigo[posicaoFinal] == ' ') || (vetorCodigo[posicaoFinal] == '\n');
        }
    }

    public Token obterToken() {
        int estado = 0;

        lexema = "";

        saltarEspacos();

        posicaoInicial = posicaoFinal;

        char letraAtual = vetorCodigo[posicaoFinal];
        int offset;

        while (letraAtual != '$') {
            letraAtual = vetorCodigo[posicaoFinal];

            switch (estado) {
                case 0:
                    if (Character.isLetter(letraAtual))
                        estado = 1;
                    else if (Character.isDigit(letraAtual))
                        estado = 3;
                    else if (letraAtual == '<')
                        estado = 7;
                    else if (letraAtual == '>')
                        estado = 11;
                    else if (letraAtual == '=')
                        estado = 14;
                    else if (letraAtual == ';')
                        estado = 15;
                    else if (letraAtual == ':')
                        estado = 16;
                    else if (letraAtual == '(')
                        estado = 19;
                    else if (letraAtual == ')')
                        estado = 20;
                    else if (letraAtual == '"')
                        estado = 21;
                    else if (letraAtual == '+')
                        estado = 23;
                    else if (letraAtual == '-')
                        estado = 24;
                    else if (letraAtual == '*')
                        estado = 25;
                    else if (letraAtual == '/')
                        estado = 26;
                    break;
                case 1:
                    if (!Character.isLetterOrDigit(letraAtual))
                        estado = 2;
                    break;
                case 2:
                    posicaoFinal--;
                    offset = posicaoFinal - posicaoInicial;
                    lexema = String.copyValueOf(vetorCodigo, posicaoInicial, offset);
                    return tabelaSimbolos.obterToken(lexema);
                case 3:
                    if (letraAtual == '.')
                        estado = 4;
                    else if (!Character.isDigit(letraAtual))
                        estado = 6;
                    break;
                case 4:
                    if (!Character.isDigit(letraAtual))
                        estado = 5;
                    break;
                case 5:
                    posicaoFinal--;
                    offset = posicaoFinal - posicaoInicial;
                    lexema = String.copyValueOf(vetorCodigo, posicaoInicial, offset);
                    return Token.NUMERO_REAL;
                case 6:
                    posicaoFinal--;
                    offset = posicaoFinal - posicaoInicial;
                    lexema = String.copyValueOf(vetorCodigo, posicaoInicial, offset);
                    return Token.NUMERO_INTEIRO;
                case 7:
                    if (letraAtual == '=')
                        estado = 8;
                    else if (letraAtual == '>')
                        estado = 9;
                    else
                        estado = 10;
                    break;
                case 8:
                    lexema = "<=";
                    return Token.RELACAO;
                case 9:
                    lexema = "<>";
                    return Token.RELACAO;
                case 10:
                    posicaoFinal--;
                    lexema = "<";
                    return Token.RELACAO;
                case 11:
                    if (letraAtual == '=')
                        estado = 12;
                    else
                        estado = 13;
                    break;
                case 12:
                    lexema = ">=";
                    return Token.RELACAO;
                case 13:
                    posicaoFinal--;
                    lexema = ">";
                    return Token.RELACAO;
                case 14:
                    lexema = "=";
                    return Token.RELACAO;
                case 15:
                    lexema = ";";
                    return Token.FIM_COMANDO;
                case 16:
                    estado = (letraAtual == '=') ? 17 : 18;
                    break;
                case 17:
                    lexema = ":=";
                    return Token.ATRIBUICAO;
                case 18:
                    posicaoFinal--;
                    lexema = ":";
                    return Token.DOIS_PONTOS;
                case 19:
                    lexema = "(";
                    return Token.ABRE_PARENTESES;
                case 20:
                    lexema = ")";
                    return Token.FECHA_PARENTESES;
                case 21:
                    if (letraAtual == '"')
                        estado = 22;
                    break;
                case 22:
                    offset = posicaoFinal - posicaoInicial;
                    lexema = String.copyValueOf(vetorCodigo, posicaoInicial, offset); // inclui as aspas
                    return Token.CADEIA_CARACTERES;
                case 23:
                    lexema = "+";
                    return Token.ADICAO;
                case 24:
                    lexema = "-";
                    return Token.SUBTRACAO;
                case 25:
                    lexema = "*";
                    return Token.MULTIPLICACAO;
                case 26:
                    lexema = "/";
                    return Token.DIVISAO;

            }

            posicaoFinal++;
        }

        return null;
    }

}
