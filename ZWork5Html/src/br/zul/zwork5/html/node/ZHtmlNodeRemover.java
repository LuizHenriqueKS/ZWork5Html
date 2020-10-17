package br.zul.zwork5.html.node;

import br.zul.zwork5.html.filter.ZHtmlNode;

/**
 *
 * @author luizh
 */
class ZHtmlNodeRemover {

    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final ZHtmlNode node;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeRemover(ZHtmlNode node) {
        this.node = node;
    }

    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    boolean remove() {
        if (node.getParent()==null){
            return false;
        } else if (node.getParent().hasChild(node)){
            node.getParent().removeChild(node);
            return true;
        } else {
            return false;
        }
    }
    
}
