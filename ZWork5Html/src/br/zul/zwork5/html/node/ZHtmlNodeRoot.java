package br.zul.zwork5.html.node;

import br.zul.zwork5.html.filter.ZHtmlNode;
import br.zul.zwork5.html.query.ZHtmlQuery;
import br.zul.zwork5.html.stringify.ZHtmlNodeDefaultStringify;
import br.zul.zwork5.util.ZList;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeRoot implements ZHtmlNode, ZHtmlNodeParent {

    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final ZList<ZHtmlNode> nodeList;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeRoot(){
        this.nodeList = new ZList<>();
    }
    
    //==========================================================================
    //MÉTODOS PÚBLICOS SOBRESCRITOS
    //==========================================================================
    @Override
    public ZList<ZHtmlNode> listChildren() {
        return new ZList<>(nodeList);
    }

    @Override
    public boolean hasChildren() {
        return !nodeList.isEmpty();
    }

    @Override
    public ZHtmlQuery query() {
        return new ZHtmlQuery(this);
    }

    @Override
    public void addChild(ZHtmlNode node) {
        nodeList.add(node);
        node.setParent(this);
    }
    
    @Override
    public void addChild(int index, ZHtmlNode node) {
        nodeList.add(index, node);
        node.setParent(this);
    }
    
    @Override
    public ZHtmlNode getChild(int index) {
        return this.nodeList.get(index);
    }

    @Override
    public ZHtmlNode removeChild(int index) {
        ZHtmlNode node = getChild(index);
        nodeList.remove(index);
        node.setParent(null);
        return node;
    }

    @Override
    public void removeChild(ZHtmlNode node) {
        nodeList.remove(node);
        node.setParent(null);
    }
    
    @Override
    public String toString(){
        return new ZHtmlNodeDefaultStringify().tryStringifyNode(this);
    }

    @Override
    public boolean remove() {
        return false;
    }

    @Override
    public boolean hasChild(ZHtmlNode node) {
        return nodeList.contains(node);
    }

    @Override
    public ZHtmlNodeParent getParent() {
        return this;
    }
    @Override
    public void setParent(ZHtmlNodeParent parent) {}

    
}
