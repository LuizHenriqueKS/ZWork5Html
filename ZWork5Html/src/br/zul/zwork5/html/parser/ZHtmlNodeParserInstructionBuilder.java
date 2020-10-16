package br.zul.zwork5.html.parser;

import br.zul.zwork5.html.exception.ZHtmlParseException;

/**
 *
 * @author luiz.silva
 */
public interface ZHtmlNodeParserInstructionBuilder {

    ZHtmlNodeParserInstructionData parseData(ZHtmlNodeParserSourceReader srcReader) throws ZHtmlNodeParserInstructionBuilderException;
    ZHtmlNodeParserInstruction build(ZHtmlNodeParserSourceReader srcReader, ZHtmlNodeParserInstructionData data) throws ZHtmlParseException;
    
}
