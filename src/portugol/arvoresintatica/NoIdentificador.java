/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.arvoresintatica;

import portugol.lexico.DadosIdentificador;

/**
 *
 * @author Kennedy
 */
public class NoIdentificador extends NoExpressao {
    private DadosIdentificador dadosIdentificador;
    public NoIdentificador(DadosIdentificador dadosIdentificador,int numeroLinha){
        super(numeroLinha);
        this.dadosIdentificador = dadosIdentificador;
    }

    public TipoValor obterTipoValorExpressao(){
        return dadosIdentificador.obterTipo();
    }

    public String obterLexema(){
        return dadosIdentificador.obterNome();
    }

    public TipoExpressao obterTipo(){
        return TipoExpressao.IDENTIFICADOR;
    }

    public void definirTipoValor(TipoValor tipoValor){
        dadosIdentificador.definirTipoValor(tipoValor);
    }

}
