/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.arvoresintatica;

import portugol.arvoresintatica.TipoExpressao;

/**
 *
 * @author Kennedy
 */
public class NoExpressaoRelacional extends NoExpressao {

    private TipoRelacao relacao;
    private NoExpressao operandoEsquerdo;
    private NoExpressao operandoDireito;

    public NoExpressaoRelacional(TipoRelacao valorRelacao, NoExpressao operandoEsquerdo, NoExpressao operandoDireito, int numeroLinha){
        super(numeroLinha);
        this.relacao = valorRelacao;
        this.operandoEsquerdo = operandoEsquerdo;
        this.operandoDireito = operandoDireito;
    }

    public TipoRelacao obterRelacao(){
        return relacao;
    }
    public NoExpressao obterOperandoEsquerdo(){
        return operandoEsquerdo;
    }

    public NoExpressao obterOperandoDireito(){
        return operandoDireito;
    }

     public TipoValor obterTipoValorExpressao(){
        return TipoValor.LOGICO;
    }

    public TipoExpressao obterTipo(){
        return TipoExpressao.EXPRESSAO_RELACIONAL;
    }

}
