/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.intermediario;

/**
 *
 * @author Kennedy
 */
public class Rotulo extends Instrucao {
    private int numeroRotulo;
    public Rotulo(int numeroRotulo){
        this.numeroRotulo = numeroRotulo;
    }

    public int obterNumeroRotulo(){
        return numeroRotulo;
    }

}
