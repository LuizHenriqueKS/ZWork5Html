package br.zul.zwork5.html.parser;

import br.zul.zwork5.html.exception.ZHtmlParseException;
import br.zul.zwork5.html.parser.instruction.ZHtmlNodeParserInstructionCloseElementBuilder;
import br.zul.zwork5.html.parser.instruction.ZHtmlNodeParserInstructionCommentBuilder;
import br.zul.zwork5.html.parser.instruction.ZHtmlNodeParserInstructionOpenElementBuilder;
import br.zul.zwork5.html.parser.instruction.ZHtmlNodeParserInstructionTextBuilder;
import br.zul.zwork5.html.parser.instruction.ZHtmlNodeParserInstructionUnknownBuilder;
import br.zul.zwork5.util.ZList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeParserInstructionFactory {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final List<ZHtmlNodeParserInstructionBuilder> builderList;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeParserInstructionFactory(){
        this.builderList = new ArrayList<>();
        this.builderList.add(new ZHtmlNodeParserInstructionOpenElementBuilder());
        this.builderList.add(new ZHtmlNodeParserInstructionCloseElementBuilder());
        this.builderList.add(new ZHtmlNodeParserInstructionUnknownBuilder());
        this.builderList.add(new ZHtmlNodeParserInstructionCommentBuilder());
        this.builderList.add(new ZHtmlNodeParserInstructionTextBuilder());
    }
    
    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    public List<ZHtmlNodeParserInstruction> parseList(ZHtmlNodeParserSourceReader srcReader) throws ZHtmlParseException {
        List<ZHtmlNodeParserInstruction> instList = new ArrayList<>();
        ZHtmlNodeParserInstruction inst;
        while ((inst=findNext(srcReader))!=null){
            instList.add(inst);
        }
        return instList;
    }

    public ZHtmlNodeParserInstruction findNext(ZHtmlNodeParserSourceReader srcReader) throws ZHtmlParseException {
        ZHtmlNodeParserInstructionData bestData = findBestData(srcReader);
        if (bestData==null){
            return null;
        } else {
            ZHtmlNodeParserInstructionBuilder builder = bestData.getInstructionBuilder();
            return builder.build(srcReader, bestData);
        }
    }
    
    //==========================================================================
    //MÉTODOS PRIVADOS
    //==========================================================================
    private ZHtmlNodeParserInstructionData findBestData(ZHtmlNodeParserSourceReader srcReader) {
        ZList<ZHtmlNodeParserInstructionData> dataList = new ZList<>();
        for (ZHtmlNodeParserInstructionBuilder builder: builderList){
            try {
                ZHtmlNodeParserInstructionData data = builder.parseData(srcReader);
                if (data!=null){
                    dataList.add(data);
                }
            } catch (ZHtmlNodeParserInstructionBuilderException ex) {}
        }
        dataList.sort(this::bestToWorstData);
        return dataList.first().orElse(null);
    }
    
    private int bestToWorstData(ZHtmlNodeParserInstructionData a, ZHtmlNodeParserInstructionData b){
        int result = a.getStart() - b.getStart();
        if (result!=0){
            return result;
        } else {
            return b.getEnd() - a.getEnd();
        }
    }
    
}
