package br.zul.zwork5.html.parser.instruction;

import br.zul.zwork5.html.node.ZHtmlComment;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstruction;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionData;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionRunner;
import br.zul.zwork5.html.parser.ZHtmlNodeTreeBuilder;
import br.zul.zwork5.str.ZStr;

/**
 *
 * @author luiz.silva
 */
class ZHtmlNodeParserInstructionComment implements ZHtmlNodeParserInstruction {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final ZStr source;
    private final ZHtmlNodeParserInstructionData data;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeParserInstructionComment(ZStr source, ZHtmlNodeParserInstructionData data) {
        this.data = data;
        this.source = source;
    }

    //==========================================================================
    //MÉTODOS PÚBLICOS SOBRESCRITOS
    //==========================================================================
    @Override
    public void preRun(ZHtmlNodeParserInstructionRunner runner) {
        
    }

    @Override
    public void run(ZHtmlNodeTreeBuilder treeBuilder) {
        treeBuilder.addNode(new ZHtmlComment(source.toString()));
    }
    
}
