package br.zul.zwork5.html.parser.instruction;

import br.zul.zwork5.html.exception.ZHtmlParseException;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstruction;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionBuilder;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionBuilderException;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionData;
import br.zul.zwork5.html.parser.ZHtmlNodeParserSourceReader;
import br.zul.zwork5.str.search.ZStrSearchResult;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeParserInstructionCommentBuilder implements ZHtmlNodeParserInstructionBuilder {

    @Override
    public ZHtmlNodeParserInstructionData parseData(ZHtmlNodeParserSourceReader srcReader) throws ZHtmlNodeParserInstructionBuilderException {
        return srcReader.findData(this, "<!--");
    }

    @Override
    public ZHtmlNodeParserInstruction build(ZHtmlNodeParserSourceReader srcReader, ZHtmlNodeParserInstructionData data) throws ZHtmlParseException {
        ZStrSearchResult result = srcReader.find(data, "-->", "<");
        if (result==null){
             return new ZHtmlNodeParserInstructionOpenElement(srcReader.readTillEnd(data), data);
        } else switch (result.getPattern()){
            case "-->":
                return new ZHtmlNodeParserInstructionComment(srcReader.read(data, result.getStartIndex(), result.getEndIndex()), data);
            case "<":
                return new ZHtmlNodeParserInstructionComment(srcReader.read(data, result.getStartIndex(), result.getStartIndex()), data);
            default:
                throw new ZHtmlParseException("Unespected pattern: " + result.getPattern());
        }
        
    }
    
}
