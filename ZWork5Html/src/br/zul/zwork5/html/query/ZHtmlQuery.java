package br.zul.zwork5.html.query;

import br.zul.zwork5.html.filter.ZHtmlNode;
import br.zul.zwork5.html.node.ZHtmlElement;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlQuery {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final ZHtmlNode node;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlQuery(ZHtmlNode node) {
        this.node = node;
    }
    
    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    public ZHtmlQueryCollection<ZHtmlNode> nodes(){
        return new ZHtmlQueryCollection<>(node.listChildren(), ZHtmlNode.class);
    }
    
    public ZHtmlQueryCollection<ZHtmlElement> elements(){
        return new ZHtmlQueryCollection<>(node.listChildren(), ZHtmlElement.class);
    }
    
}
