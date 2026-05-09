/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.arvoresintatica;


/**
 *
 * @author Kennedy
 */
public abstract class NoExpressao extends NoSintatico {


    public NoExpressao(int numeroLinha){
        super(numeroLinha);
    }
    
    public abstract TipoValor obterTipoValorExpressao();
    public abstract TipoExpressao obterTipo();
}
