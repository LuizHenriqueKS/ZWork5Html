package br.zul.zwork5.html.parser;

/**
 *
 * @author luiz.silva
 */
public interface ZHtmlNodeParserInstruction {
    
    void preRun(ZHtmlNodeParserInstructionRunner runner);
    void run(ZHtmlNodeTreeBuilder treeBuilder);
    
}
