package br.zul.zwork5.html.parser;

import br.zul.zwork5.util.ZListMap;
import java.util.List;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeParserInstructionRunner {

    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final List<ZHtmlNodeParserInstruction> instList;
    private final ZListMap<String, ZHtmlNodeParserInstruction> listMapInst;

    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeParserInstructionRunner(List<ZHtmlNodeParserInstruction> instList){
        this.instList = instList;
        this.listMapInst = new ZListMap<>();
    }
    
    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    public void run(ZHtmlNodeTreeBuilder treeBuilder) {
        preRun();
        for (ZHtmlNodeParserInstruction instruction:instList){
            instruction.run(treeBuilder);
        }
    }
    
    //==========================================================================
    //MÉTODOS PRIVADOS
    //==========================================================================
    private void preRun() {
        for (ZHtmlNodeParserInstruction instruction:instList){
            instruction.preRun(this);
        }
    }

    public void addAlias(String alias, ZHtmlNodeParserInstruction instruction) {
        listMapInst.getOrNew(alias).add(instruction);
    }
    
    public ZHtmlNodeParserInstruction getByAlias(String alias){
        return listMapInst.get(alias).last().orElse(null);
    }
    
    public void removeAlias(String alias, ZHtmlNodeParserInstruction instruction){
        listMapInst.getOrNew(alias).remove(instruction);
    }

    public ZHtmlNodeParserInstruction removeLastAlias(String alias) {
        ZHtmlNodeParserInstruction instruction = getByAlias(alias);
        removeAlias(alias, instruction);
        return instruction;
    }
    
}
