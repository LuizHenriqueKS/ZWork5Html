package br.zul.zwork5.html.parser.instruction;

import br.zul.zwork5.html.node.ZHtmlElement;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstruction;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionData;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionRunner;
import br.zul.zwork5.html.parser.ZHtmlNodeTreeBuilder;
import br.zul.zwork5.str.ZStr;
import br.zul.zwork5.str.search.ZStrSearchResult;
import br.zul.zwork5.util.ZPair;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 * @author luiz.silva
 */
class ZHtmlNodeParserInstructionCloseElement implements ZHtmlNodeParserInstruction {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final ZStr source;
    private final ZHtmlNodeParserInstructionData data;
    
    private String tagName;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeParserInstructionCloseElement(ZStr source, ZHtmlNodeParserInstructionData data) {
        this.data = data;
        this.source = source.trim();
    }

    //==========================================================================
    //MÉTODOS PÚBLICOS SOBRESCRITOS
    //==========================================================================
    @Override
    public void preRun(ZHtmlNodeParserInstructionRunner runner) {
        try {
            ZHtmlNodeParserInstruction inst = runner.removeLastAlias("element:"+getTagName());
            ((ZHtmlNodeParserInstructionOpenElement)inst).setHasChildren(true);
        } catch (NullPointerException|NoSuchElementException ex){}
    }

    @Override
    public void run(ZHtmlNodeTreeBuilder treeBuilder) {
        treeBuilder.closeElement(getTagName());
    }
    
    //==========================================================================
    //MÉTODOS PRIVADOS
    //==========================================================================
    private String getTagName() {
        if (tagName==null){
            tagName = source.toString();
        }
        return tagName;
    }
    
}
