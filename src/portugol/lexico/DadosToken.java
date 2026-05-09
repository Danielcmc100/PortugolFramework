/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.lexico;

/**
 *
 * @author Chessman
 */
public class DadosToken {
    private Token token;
    
    public Token obterToken(){
        return token;
    }

    public DadosToken(Token token){
        this.token = token;
    }    
}
