package br.zul.zwork5.html.stringify;

import br.zul.zwork5.exception.ZUnexpectedException;
import br.zul.zwork5.html.exception.ZHtmlNodeStringifyException;
import br.zul.zwork5.html.filter.ZHtmlNode;
import br.zul.zwork5.html.node.ZHtmlComment;
import br.zul.zwork5.html.node.ZHtmlElement;
import br.zul.zwork5.html.node.ZHtmlNodeRoot;
import br.zul.zwork5.html.node.ZHtmlText;
import br.zul.zwork5.html.node.ZHtmlUnknown;
import java.util.Map.Entry;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlNodeDefaultStringify implements ZHtmlNodeStringify{

    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    @Override
    public String stringifyNode(ZHtmlNode node) throws ZHtmlNodeStringifyException {
        if (node instanceof ZHtmlText) {
            return stringifyText((ZHtmlText)node);
        } else if (node instanceof ZHtmlElement){
            return stringifyElement((ZHtmlElement)node);
        } else if (node instanceof ZHtmlUnknown){
            return stringifyUnknown((ZHtmlUnknown)node);
        } else if (node instanceof ZHtmlNodeRoot){
            return stringifyRoot((ZHtmlNodeRoot)node);
        } else if (node instanceof ZHtmlComment){
            return stringifyComment((ZHtmlComment)node);
        } else if (node==null){
            throw new NullPointerException();
        } else {
            throw new ZHtmlNodeStringifyException("Unexpected node of class: {0}", node.getClass());
        }
    }
    
    public String tryStringifyNode(ZHtmlNode node){
        try {
            return stringifyNode(node);
        } catch (ZHtmlNodeStringifyException ex){
            throw new ZUnexpectedException(ex);
        }
    }
    
    //==========================================================================
    //MÉTODOS PRIVADOS
    //==========================================================================
    private String stringifyText(ZHtmlText text) {
        return text.getContent();
    }

    private String stringifyElement(ZHtmlElement element) throws ZHtmlNodeStringifyException {
        StringBuilder builder = new StringBuilder();
        if ("br".equalsIgnoreCase(element.getTagName())){
            builder.append("<br/>");
        } else {
            builder.append("<");
            builder.append(element.getTagName());
            builder.append(getAttributes(element));
            builder.append(">");
            
            StringBuilder childBuilder = new StringBuilder();
            if (element.hasChildren()){
                childBuilder.append("\r\n");
                for (ZHtmlNode node:element.listChildren()){
                    childBuilder.append("\t");
                    childBuilder.append(stringifyNode(node).replace("\r\n", "\r\n\t"));
                    childBuilder.append("\r\n");
                }
            }
            if (childBuilder.toString().trim().contains("\n")){
                builder.append(childBuilder.toString());
            } else {
                builder.append(childBuilder.toString().trim());
            }
            
            builder.append("</");
            builder.append(element.getTagName());
            builder.append(">");
        }
        return builder.toString();
    }

    private String stringifyUnknown(ZHtmlUnknown unknown) {
        StringBuilder builder = new StringBuilder();
        builder.append("<");
        builder.append(unknown.getContent());
        builder.append(">");
        return builder.toString();
    }

    private String stringifyRoot(ZHtmlNodeRoot root) throws ZHtmlNodeStringifyException {
        StringBuilder builder = new StringBuilder();
        for (ZHtmlNode node:root.listChildren()){
            builder.append(stringifyNode(node));
            builder.append("\r\n");
        }
        return builder.toString();
    }

    private String stringifyComment(ZHtmlComment comment) {
        StringBuilder builder = new StringBuilder();
        builder.append("<!--");
        builder.append(comment.getContent());
        builder.append("-->");
        return builder.toString();
    }

    private String getAttributes(ZHtmlElement element) {
        StringBuilder result = new StringBuilder();
        for (Entry<String, String> e:element.rawAttributeMap().entrySet()){
            result.append(" ");
            if (e.getValue().isEmpty()) {
                result.append(e.getKey());
            } else {
                result.append(e.getKey());
                result.append("=");
                result.append("\"");
                result.append(e.getValue());
                result.append("\"");
            }
        }
        return result.toString();
    }
    
}
