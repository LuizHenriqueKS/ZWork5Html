package br.zul.zwork5.html.parser;

import br.zul.zwork5.util.ZValidations;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeParserInstructionDataBuilder {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private Integer start;
    private Integer end;
    private String pattern;
    private ZHtmlNodeParserInstructionBuilder instructionBuilder;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    
    
    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    public ZHtmlNodeParserInstructionData build(){
        validate();
        return implement();
    }

    //==========================================================================
    //MÉTODOS PRIVADOS
    //==========================================================================
    private void validate() {
        ZValidations.requireNonNull(start);
        ZValidations.requireNonNull(end);
        ZValidations.requireContent(pattern);
        ZValidations.requireNonNull(instructionBuilder);
    }

    private ZHtmlNodeParserInstructionData implement() {
        ZHtmlNodeParserInstructionData data = new ZHtmlNodeParserInstructionData();
        data.start = start;
        data.end = end;
        data.pattern = pattern;
        data.instructionBuilder = instructionBuilder;
        return data;
    }

    //==========================================================================
    //GETTERS E SETTERS
    //==========================================================================
    public Integer getStart() {
        return start;
    }
    public ZHtmlNodeParserInstructionDataBuilder setStart(Integer start) {
        this.start = start;
        return this;
    }

    public Integer getEnd() {
        return end;
    }
    public ZHtmlNodeParserInstructionDataBuilder setEnd(Integer end) {
        this.end = end;
        return this;
    }

    public String getPattern() {
        return pattern;
    }
    public ZHtmlNodeParserInstructionDataBuilder setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public ZHtmlNodeParserInstructionBuilder getInstructionBuilder() {
        return instructionBuilder;
    }
    public ZHtmlNodeParserInstructionDataBuilder setInstructionBuilder(ZHtmlNodeParserInstructionBuilder instructionBuilder) {
        this.instructionBuilder = instructionBuilder;
        return this;
    }
    
}
