package br.zul.zwork5.html.filter;

import br.zul.zwork5.html.query.ZHtmlQuery;
import br.zul.zwork5.util.ZList;

/**
 *
 * @author luiz.silva
 */
public interface ZHtmlNode {
    
    boolean hasChildren();
    ZList<ZHtmlNode> listChildren();
    
    default ZHtmlQuery query(){
        return new ZHtmlQuery(this);
    }
    
}
