package br.zul.zwork5.html.parser.instruction;

import br.zul.zwork5.html.node.ZHtmlUnknown;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstruction;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionData;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionRunner;
import br.zul.zwork5.html.parser.ZHtmlNodeTreeBuilder;
import br.zul.zwork5.str.ZStr;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author luiz.silva
 */
class ZHtmlNodeParserInstructionUnknown implements ZHtmlNodeParserInstruction {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final ZStr source;
    private final ZHtmlNodeParserInstructionData data;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeParserInstructionUnknown(ZStr source, ZHtmlNodeParserInstructionData data) {
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
        treeBuilder.addNode(new ZHtmlUnknown(source.toString()));
    }
    
}
