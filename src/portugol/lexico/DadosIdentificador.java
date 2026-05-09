/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.lexico;

import portugol.arvoresintatica.TipoValor;

public class DadosIdentificador extends DadosToken {
    
    private String nome;
    private TipoValor tipoValor;

    public DadosIdentificador(String nome){
        super(Token.IDENTIFICADOR);
        this.nome = nome;
        this.tipoValor = TipoValor.INDEFINIDO;
    }
    public String obterNome(){
        return nome;
    }
    public TipoValor obterTipo(){
        return tipoValor;
    }
    public void definirTipoValor(TipoValor tipoValor){
        this.tipoValor = tipoValor;
    }
}
