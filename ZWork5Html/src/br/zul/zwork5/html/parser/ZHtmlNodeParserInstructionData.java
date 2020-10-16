package br.zul.zwork5.html.parser;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeParserInstructionData {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    protected int start;
    protected int end;
    protected String pattern;
    protected ZHtmlNodeParserInstructionBuilder instructionBuilder;
    
    //==========================================================================
    //GETTERS E SETTERS
    //==========================================================================
    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getPattern() {
        return pattern;
    }

    public ZHtmlNodeParserInstructionBuilder getInstructionBuilder() {
        return instructionBuilder;
    }
    
}
