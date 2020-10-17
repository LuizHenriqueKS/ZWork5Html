package br.zul.zwork5.html.node;

import br.zul.zwork5.html.filter.ZHtmlNode;
import br.zul.zwork5.html.util.ZHtmlUtils;
import br.zul.zwork5.util.ZList;
import java.util.Objects;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlText implements ZHtmlNode{
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private String content;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlText() {
        this.content = "";
    }
    
    //==========================================================================
    //MÉTODOS PÚBLICOS SOBRESCRITOS
    //==========================================================================
    @Override
    public String toString() {
        return content;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.content);
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
        final ZHtmlText other = (ZHtmlText) obj;
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
    
    //==========================================================================
    //GETTERS E SETTERS
    //==========================================================================
    public String getText(){
        return ZHtmlUtils.unescapeHtml(content);
    }
    public ZHtmlText setText(String text){
        this.content = ZHtmlUtils.escapeHtml(content);
        return this;
    }
    
    public String getContent() {
        return content;
    }
    public ZHtmlText setContent(String content) {
        this.content = content;
        return this;
    }
    
}
