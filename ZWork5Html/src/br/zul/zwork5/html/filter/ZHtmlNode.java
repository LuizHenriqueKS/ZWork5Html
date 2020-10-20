package br.zul.zwork5.html.filter;

import br.zul.zwork5.html.node.ZHtmlNodeParent;
import br.zul.zwork5.html.query.ZHtmlQuery;
import br.zul.zwork5.util.ZList;
import java.util.NoSuchElementException;

/**
 *
 * @author luiz.silva
 */
public interface ZHtmlNode {
    
    boolean hasChildren();
    boolean remove();
    ZHtmlNode getChild(int index) throws NoSuchElementException;
    ZList<ZHtmlNode> listChildren();
    
    public ZHtmlNodeParent getParent();
    public void setParent(ZHtmlNodeParent parent);
    
    default ZHtmlQuery query(){
        return new ZHtmlQuery(this);
    }
    
}
