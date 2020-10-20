package br.zul.zwork5.html.node;

import br.zul.zwork5.html.filter.ZHtmlNode;
import br.zul.zwork5.html.stringify.ZHtmlNodeDefaultStringify;
import br.zul.zwork5.html.util.ZHtmlUtils;
import br.zul.zwork5.util.ZList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlComment implements ZHtmlNode {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private String content;
    private ZHtmlNodeParent parent;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlComment(String content) {
        Objects.requireNonNull(content);
        this.content = content;
    }

    //==========================================================================
    //MÉTODOS PÚBLICOS SOBRESCRITOS
    //==========================================================================
    @Override
    public String toString(){
        return new ZHtmlNodeDefaultStringify().tryStringifyNode(this);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.content);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ZHtmlComment other = (ZHtmlComment) obj;
        return Objects.equals(this.content, other.content);
    }
    
    @Override
    public boolean hasChildren() {
        return false;
    }

    @Override
    public ZList<ZHtmlNode> listChildren() {
        return new ZList<>();
    }

    @Override
    public ZHtmlNode getChild(int index) throws NoSuchElementException {
        throw new NoSuchElementException();
    }

    @Override
    public boolean remove() {
        return new ZHtmlNodeRemover(this).remove();
    }
    
    //==========================================================================
    //GETTERS E SETTERS
    //==========================================================================
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getText(){
        return ZHtmlUtils.unescapeHtml(content);
    }
    public void setText(String text){
        this.content = ZHtmlUtils.escapeHtml(content);
    }

    @Override
    public ZHtmlNodeParent getParent() {
        return parent;
    }
    @Override
    public void setParent(ZHtmlNodeParent parent) {
        this.parent = parent;
    }

}
