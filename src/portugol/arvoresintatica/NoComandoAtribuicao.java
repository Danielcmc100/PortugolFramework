/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.arvoresintatica;


/**
 *
 * @author Kennedy
 */
public class NoComandoAtribuicao extends NoComando {

    private NoIdentificador identificador;

    //A operacao pode ser um Token ou uma Expressao Aritmetica
    private NoExpressao expressao;

    public NoComandoAtribuicao(NoIdentificador identificador, 
                               NoExpressao expressao, 
                               int numeroLinha){
        super(numeroLinha);
        this.identificador = identificador;
        this.expressao = expressao;
    }

    public NoIdentificador obterIdentificador(){
        return identificador;
    }

    public NoExpressao obterExpressao(){
        return expressao;
    }

    public NomeComando obterNome(){
        return NomeComando.ATRIBUICAO;
    }


}
