package br.zul.zwork5.html.parser;

import br.zul.zwork5.html.filter.ZHtmlNode;
import br.zul.zwork5.html.node.ZHtmlElement;
import br.zul.zwork5.html.node.ZHtmlNodeFather;
import java.util.Stack;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeTreeBuilder {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final ZHtmlNodeFather root;
    private final Stack<ZHtmlNodeFather> fatherStack;
    
    private ZHtmlNodeFather currentFather;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeTreeBuilder(ZHtmlNodeFather root) {
        this.root = root;
        this.fatherStack = new Stack<>();
        this.currentFather = root;
    }

    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    public void addElementWithChildren(ZHtmlElement element) {
        addNode(element);
        fatherStack.push(currentFather);
        currentFather = element;
    }
    
    public void addElementWithoutChildren(ZHtmlElement element) {
        addNode(element);
    }
    
    public void addNode(ZHtmlNode node){
        currentFather.add(node);
    }
    
    public void closeElement(String tagName){
        if (fatherStack.isEmpty()){
            currentFather = root;
        } else {
            currentFather = fatherStack.pop();
        }
    }
    
}
