package br.zul.zwork5.html.parser.instruction;

import br.zul.zwork5.html.node.ZHtmlText;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstruction;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionData;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionRunner;
import br.zul.zwork5.html.parser.ZHtmlNodeTreeBuilder;
import br.zul.zwork5.str.ZStr;

/**
 *
 * @author luiz.silva
 */
class ZHtmlNodeParserInstructionText implements ZHtmlNodeParserInstruction {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final ZStr source;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeParserInstructionText(ZStr source, ZHtmlNodeParserInstructionData data) {
        ZStr tmp = source.prepend(data.getPattern()).replace("\r\n", "\n");
        StringBuilder result = new StringBuilder();
        for (ZStr row:tmp.split("\n")){
            result.append(row.trimStart());
        }
        this.source = new ZStr(result.toString());
    }

    //==========================================================================
    //MÉTODOS PÚBLICOS SOBRESCRITOS
    //==========================================================================
    @Override
    public void preRun(ZHtmlNodeParserInstructionRunner runner) {
        
    }

    @Override
    public void run(ZHtmlNodeTreeBuilder treeBuilder) {
        if (!source.toString().trim().isEmpty()){
            treeBuilder.addNode(new ZHtmlText().setContent(source.toString()));
        }
    }
    
}
