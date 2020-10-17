package br.zul.zwork5.html.parser;

import br.zul.zwork5.html.filter.ZHtmlNode;
import br.zul.zwork5.html.node.ZHtmlElement;
import java.util.Stack;
import br.zul.zwork5.html.node.ZHtmlNodeParent;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeTreeBuilder {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final ZHtmlNodeParent root;
    private final Stack<ZHtmlNodeParent> fatherStack;
    
    private ZHtmlNodeParent currentFather;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeTreeBuilder(ZHtmlNodeParent root) {
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
        currentFather.addChild(node);
    }
    
    public void closeElement(String tagName){
        if (fatherStack.isEmpty()){
            currentFather = root;
        } else {
            currentFather = fatherStack.pop();
        }
    }
    
}
