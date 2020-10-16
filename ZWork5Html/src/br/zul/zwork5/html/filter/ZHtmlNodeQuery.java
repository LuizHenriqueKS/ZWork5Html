package br.zul.zwork5.html.filter;

import br.zul.zwork5.html.node.ZHtmlElement;
import br.zul.zwork5.stream.ZStream;
import br.zul.zwork5.util.ZList;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 *
 * @author luiz.silva
 */
interface ZHtmlNodeQuery {
    
    public ZHtmlNode getNode(ZHtmlNodeFilter filter) throws NoSuchElementException;
    public ZList<ZHtmlNode> listNodes(ZHtmlNodeFilter filter);
    public ZStream<ZHtmlNode> streamNodes(ZHtmlNodeFilter filter);
    public boolean hasNodes(ZHtmlNodeFilter filter);
    public int countNodes(ZHtmlNodeFilter filter);
    
    public ZHtmlElement getElement(ZHtmlElementFilter filter) throws NoSuchElementException;
    public ZList<ZHtmlElement> listElements(ZHtmlElementFilter filter);
    public Stream<ZHtmlElement> streamElements(ZHtmlElementFilter filter);
    public boolean hasElements(ZHtmlElementFilter filter);
    public int countElements(ZHtmlElementFilter filter);
    
}
