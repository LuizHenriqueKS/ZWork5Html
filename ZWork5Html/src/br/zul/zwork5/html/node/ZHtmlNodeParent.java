package br.zul.zwork5.html.node;

import br.zul.zwork5.html.filter.ZHtmlNode;

/**
 *
 * @author luiz.silva
 */
public interface ZHtmlNodeParent {
    
    void addChild(ZHtmlNode node);
    void addChild(int index, ZHtmlNode node);
    void removeChild(ZHtmlNode node);
    ZHtmlNode removeChild(int index);
    boolean hasChild(ZHtmlNode node);
    
}
