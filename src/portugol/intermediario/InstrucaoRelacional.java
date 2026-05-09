/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.intermediario;

import portugol.arvoresintatica.NoExpressao;
import portugol.arvoresintatica.NoIdentificador;
import portugol.arvoresintatica.TipoRelacao;

/**
 *
 * @author Kennedy
 */
public class InstrucaoRelacional extends Instrucao{
    private  TipoRelacao valor;
    private NoIdentificador identificador;
    private NoExpressao operandoEsquerdo;
    private NoExpressao operandoDireito;

    public InstrucaoRelacional(TipoRelacao valorRelacao,
                               NoIdentificador identificador,
                               NoExpressao operandoEsquerdo,
                               NoExpressao operandoDireito){

        this.valor = valorRelacao;
        this.identificador = identificador;
        this.operandoEsquerdo = operandoEsquerdo;
        this.operandoDireito = operandoDireito;
    }

    public TipoRelacao obterTipoOperacao(){
        return valor;
    }

    public NoIdentificador obterOperandoRetorno(){
        return identificador;
    }

    public NoExpressao obterOperandoEsquerdo(){
        return operandoEsquerdo;
    }

    public NoExpressao obterOperandoDireito(){
        return operandoDireito;
    }

}
