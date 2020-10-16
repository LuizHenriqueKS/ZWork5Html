package br.zul.zwork5.html.node;

import br.zul.zwork5.html.filter.ZHtmlNode;
import br.zul.zwork5.html.query.ZHtmlQuery;
import br.zul.zwork5.html.stringify.ZHtmlNodeDefaultStringify;
import br.zul.zwork5.util.ZList;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeRoot implements ZHtmlNode, ZHtmlNodeFather {

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
    public void add(ZHtmlNode node) {
        nodeList.add(node);
    }

    @Override
    public void remove(ZHtmlNode node) {
        nodeList.remove(node);
    }
    
    @Override
    public String toString(){
        return new ZHtmlNodeDefaultStringify().tryStringifyNode(this);
    }
    
}
