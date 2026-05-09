/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.compilador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import portugol.lexico.DadosIdentificador;
import portugol.lexico.DadosToken;
import portugol.lexico.Lexema;
import portugol.lexico.Token;

public class TabelaSimbolos {

    private Map<String, DadosToken> dadosTokens;

    public TabelaSimbolos() {
        dadosTokens = new HashMap<String, DadosToken>();
    }

    public void iniciar() {
        dadosTokens.clear();
        dadosTokens.put(Lexema.PROGRAMA, new DadosToken(Token.PROGRAMA));
        dadosTokens.put(Lexema.ENQUANTO, new DadosToken(Token.ENQUANTO));
        dadosTokens.put(Lexema.FACA, new DadosToken(Token.FACA));
        dadosTokens.put(Lexema.INICIO, new DadosToken(Token.INICIO));
        dadosTokens.put(Lexema.FIM, new DadosToken(Token.FIM));
        dadosTokens.put(Lexema.SE, new DadosToken(Token.SE));
        dadosTokens.put(Lexema.ENTAO, new DadosToken(Token.ENTAO));
        dadosTokens.put(Lexema.SENAO, new DadosToken(Token.SENAO));
        dadosTokens.put(Lexema.DE, new DadosToken(Token.DE));
        dadosTokens.put(Lexema.ATE, new DadosToken(Token.ATE));
        dadosTokens.put(Lexema.LER, new DadosToken(Token.LER));
        dadosTokens.put(Lexema.ESCREVER, new DadosToken(Token.ESCREVER));
        dadosTokens.put(Lexema.INTEIRO, new DadosToken(Token.TIPO_VARIAVEL));
        dadosTokens.put(Lexema.REAL, new DadosToken(Token.TIPO_VARIAVEL));
        dadosTokens.put(Lexema.CADEIA_CARACTERES, new DadosToken(Token.TIPO_VARIAVEL));
    }

    public DadosToken obterDadosToken(String lexema) {
        String lexemaCaixaBaixa = lexema.toLowerCase();
        DadosToken dadosToken = dadosTokens.get(lexemaCaixaBaixa);
        if (dadosToken == null) {
            dadosToken = new DadosIdentificador(lexemaCaixaBaixa);
            dadosTokens.put(lexemaCaixaBaixa, dadosToken);
        }
        return dadosToken;
    }

    public Token obterToken(String lexema) {
        String lexemaCaixaBaixa = lexema.toLowerCase();
        DadosToken dadosToken = dadosTokens.get(lexemaCaixaBaixa);

        if (dadosToken == null) {
            dadosToken = new DadosIdentificador(lexemaCaixaBaixa);
            dadosTokens.put(lexemaCaixaBaixa, dadosToken);
        }

        return dadosToken.obterToken();
    }

    public ArrayList<DadosIdentificador> obterDadosIdentificadores() {
        ArrayList<DadosIdentificador> identificadores = new ArrayList<DadosIdentificador>();
        Iterator it = dadosTokens.values().iterator();
        while (it.hasNext()) {
            Object token = it.next();
            if (token instanceof DadosIdentificador) {
                identificadores.add((DadosIdentificador) token);
            }
        }
        return identificadores;
    }
}
