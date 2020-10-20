package br.zul.zwork5.html;

import br.zul.zwork5.html.exception.ZHtmlParseException;
import br.zul.zwork5.html.filter.ZHtmlNode;
import br.zul.zwork5.html.node.ZHtmlNodeRoot;
import br.zul.zwork5.html.parser.ZHtmlNodeParser;
import br.zul.zwork5.html.query.ZHtmlQuery;
import java.util.List;

/**
 *
 * @author luiz.silva
 */
public class ZHtml {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final ZHtmlNodeRoot root;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtml() {
        this.root = new ZHtmlNodeRoot();
    }
    
    public ZHtml(String source) throws ZHtmlParseException{
        this.root = new ZHtmlNodeParser().parse(source);
    }
    
    //==========================================================================
    //MÉTODOS PÚBLICOS SOBRESCRITOS
    //==========================================================================
    @Override
    public String toString(){
        return root.toString();
    }
    
    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    public List<ZHtmlNode> listNodes() {
        return root.listChildren();
    }
    
    public boolean hasChildren(){
        return root.hasChildren();
    }
    
    public ZHtmlQuery query(){
        return new ZHtmlQuery(root);
    }
    
    public ZHtmlNodeRoot getRoot(){
        return root;
    }
    
}
