package br.zul.zwork5.html.node;

import br.zul.zwork5.html.filter.ZHtmlNode;

/**
 *
 * @author luiz.silva
 */
public interface ZHtmlNodeParent {
    
    void addChild(ZHtmlNode node);
    void removeChild(ZHtmlNode node);
    boolean hasChild(ZHtmlNode node);
    
}
