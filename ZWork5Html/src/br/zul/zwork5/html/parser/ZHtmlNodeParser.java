package br.zul.zwork5.html.parser;

import br.zul.zwork5.html.exception.ZHtmlParseException;
import br.zul.zwork5.html.node.ZHtmlNodeRoot;
import java.util.List;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeParser {

    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeParser() {
        
    }

    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    public ZHtmlNodeRoot parse(String source) throws ZHtmlParseException {
        ZHtmlNodeRoot root = new ZHtmlNodeRoot();
        ZHtmlNodeParserSourceReader srcReader = new ZHtmlNodeParserSourceReader(this, source);
        ZHtmlNodeParserInstructionFactory instFactory = new ZHtmlNodeParserInstructionFactory();
        List<ZHtmlNodeParserInstruction> instList = instFactory.parseList(srcReader);
        ZHtmlNodeParserInstructionRunner instRunner = new ZHtmlNodeParserInstructionRunner(instList);
        instRunner.run(new ZHtmlNodeTreeBuilder(root)); 
        return root;
    }
    
}
