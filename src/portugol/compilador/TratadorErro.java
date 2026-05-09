/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portugol.compilador;
import portugol.arvoresintatica.NoComandoDeAte;
import portugol.arvoresintatica.TipoValor;
import portugol.lexico.DadosIdentificador;
import portugol.lexico.Token;
import portugol.tratamentoerros.ErroLexico;
import portugol.tratamentoerros.ErroLimitesComandoDeAte;
import portugol.tratamentoerros.ErroSintatico;
import portugol.lexico.Lexema;
import portugol.tratamentoerros.ErroExpressaoTextoNaoPermitida;
import portugol.tratamentoerros.ErroTipoIndefinido;
import portugol.tratamentoerros.ErroTiposIncompativeis;
/**
 *
 * @author Kennedy
 */
public class TratadorErro {

    public TratadorErro(){
    }

    public void emitirErroLexico(String lexemaRetornado, int linha, int coluna) throws Exception{
          throw new ErroLexico (lexemaRetornado, linha,  coluna);
    }

    public void emitirErroSintatico(Token idTokenEsperado, Token tokenRetornado, int numeroLinha) throws Exception{

        String lexemaEsperado = Lexema.obterNomeToken(idTokenEsperado);

        String lexemaRetornado = "nenhum";

        if (tokenRetornado != null){
            lexemaRetornado = Lexema.obterNomeToken(tokenRetornado);
        }

        if (lexemaEsperado != null && lexemaRetornado != null){

            throw new ErroSintatico (lexemaEsperado,
                                     lexemaRetornado,
                                     numeroLinha);

        } else {

            throw new ErroSintatico ();
            
        }
    }

    public void emitirErroLimitesComandoDeAte(NoComandoDeAte comandoDeAte) throws Exception {
        throw new ErroLimitesComandoDeAte(comandoDeAte);
    }

    public void emitirErroSemanticoTipoIndefinido(DadosIdentificador identificador) throws Exception {
            throw new ErroTipoIndefinido(identificador);
    }

    public void emitirErroSemanticoTipoValorEsperado (TipoValor tipoValorExpressaoEsperado,
                                                      TipoValor tipoValorExpressao,
                                                      int numeroLinha) throws Exception {
        throw new ErroTiposIncompativeis(tipoValorExpressaoEsperado, tipoValorExpressao, numeroLinha);

    }

    public void emitirErroExpressaoTipoTexoNaoPermitida (int numeroLinha) throws Exception {
        throw new ErroExpressaoTextoNaoPermitida (numeroLinha);
    }


}

