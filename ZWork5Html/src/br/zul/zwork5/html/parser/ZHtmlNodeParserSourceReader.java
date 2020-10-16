package br.zul.zwork5.html.parser;

import br.zul.zwork5.str.ZStr;
import br.zul.zwork5.str.search.ZStrSearchResult;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeParserSourceReader {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private ZStr source;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeParserSourceReader(ZHtmlNodeParser parser, String source) {
        this.source = new ZStr(source);
    }

    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    public ZHtmlNodeParserInstructionData findData(ZHtmlNodeParserInstructionBuilder builder, String... patterns) {
        return source.search(patterns)
                     .stream()
                     .map(r->searchResultToInstructionData(builder, r))
                     .findFirst()
                     .orElse(null);
    }

    public ZStr read(ZHtmlNodeParserInstructionData data, String... patterns) {
        source = source.from(data.getEnd());
        ZStrSearchResult searchResult = source.search(patterns).next();
        if (searchResult==null){
            ZStr result = source;
            close();
            return result;
        } else {
            ZStr result = source.till(searchResult.getStartIndex());
            source = source.from(searchResult.getEndIndex());
            return result;
        }
    }

    public ZStr read(ZHtmlNodeParserInstructionData data, int startIndex, int endIndex) {
        source = source.from(data.getEnd());
        ZStr result = source.till(startIndex);
        source = source.from(endIndex);
        return result;
    }
/*
    public ZStrSearchResult find(String... patterns) {
        return source.search(patterns).next();
    }*/

    public ZStrSearchResult find(ZHtmlNodeParserInstructionData data, String... patterns) {
        return source.from(data.getEnd()).search(patterns).next();
    }

    public ZStr readTillEnd(ZHtmlNodeParserInstructionData data) {
        ZStr result = source.from(data.getEnd());
        close();
        return result;
    }
    
    public void close(){
        source = new ZStr("");
    }
    
    
    //==========================================================================
    //MÉTODOS PRIVADOS
    //==========================================================================
    private ZHtmlNodeParserInstructionData searchResultToInstructionData(ZHtmlNodeParserInstructionBuilder builder, ZStrSearchResult searchResult){
        return new ZHtmlNodeParserInstructionDataBuilder()
                            .setStart(searchResult.getStartIndex())
                            .setEnd(searchResult.getEndIndex())
                            .setPattern(searchResult.getPattern())
                            .setInstructionBuilder(builder)
                            .build();
    }
    
}
