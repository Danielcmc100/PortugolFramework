/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.arvoresintatica;

/**
 *
 * @author Kennedy
 */
public abstract class NoComando extends NoSintatico {

    public NoComando (int numeroLinha){
        super(numeroLinha);
    }
    public abstract NomeComando obterNome();
}
