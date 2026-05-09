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
public class NoExpressaoAritmetica extends NoExpressao {
    private TipoOperacaoAritmetica codigoOperacao;
    private NoExpressao operandoEsquerdo;
    private NoExpressao operandoDireito;
    
    public NoExpressaoAritmetica(TipoOperacaoAritmetica codigoOperacao,
                                 NoExpressao operandoEsquerdo,
                                 NoExpressao operandoDireito,
                                 int numeroLinha){
        super (numeroLinha);
        this.codigoOperacao = codigoOperacao;
        this.operandoEsquerdo = operandoEsquerdo;
        this.operandoDireito = operandoDireito;
    }

    public NoExpressao obterOperandoEsquerdo(){
        return operandoEsquerdo;
    }

    public NoExpressao obterOperandoDireito(){
        return operandoDireito;
    }

    public TipoOperacaoAritmetica obterCodigoOperacao(){
        return codigoOperacao;
    }

    /*
     * O tipo de uma operacao aritmética deve ser compatível
     * com os tipos dos operandos. Quando é realizada operações com operandos
     * do mesmo tipo, o tipo da operação deve ser o mesmo que dos operandos.
     * Por exemplo, se os operandos são inteiros, a operação deve ser do tipo
     * inteiro. Contudo, existem situações em os tipos dos operandos não são
     * o mesmo,mas compatíveis. Esse é o caso, de realizar operações envolvendo
     * reais e inteiros, como, inteiro + real. Nesse caso, uma solução
     * é fazer com que tipo da operação evite perda de dados, como os valores
     * decimais. Sendo assim, inteiros operações qu envolvem inteiros e reais
     * devem ser do tipo real.
     * Nota: Se uma operacao possui outras operacoes, obterTipo será executada
     * recursivamente até que os operandos representem valores ou identificadores.
     */
    public TipoValor obterTipoValorExpressao(){
        if (operandoEsquerdo.obterTipoValorExpressao() == operandoDireito.obterTipoValorExpressao()){
            return operandoEsquerdo.obterTipoValorExpressao();
        }
        return TipoValor.REAL;
    }

    public TipoExpressao obterTipo(){
        return TipoExpressao.EXPRESSAO_ARITMETICA;
    }

}
