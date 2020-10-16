package br.zul.zwork5.html.node;

import br.zul.zwork5.html.filter.ZHtmlNode;
import br.zul.zwork5.html.stringify.ZHtmlNodeDefaultStringify;
import br.zul.zwork5.util.ZList;
import br.zul.zwork5.value.ZValue;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlElement implements ZHtmlNode, ZHtmlNodeFather {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final Map<String, String> attributeMap;
    private final ZList<ZHtmlNode> nodeList;
    
    private String tagName;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlElement() {
        this.attributeMap = new LinkedHashMap<>();
        this.nodeList = new ZList<>();
        this.tagName = "untitled";
    }
    
    //==========================================================================
    //MÉTODOS PÚBLICOS SOBRESCRITOS
    //==========================================================================
    @Override
    public boolean hasChildren() {
        return nodeList.isEmpty();
    }

    @Override
    public ZList<ZHtmlNode> listChildren() {
        return new ZList<>(nodeList);
    }

    public String getText() {
        StringBuilder builder = new StringBuilder();
        boolean espace = false;
        switch (tagName){
            case "br":
                builder.append("\r\n");
                break;
            case "p":
                builder.append("\r\n");
                break;
        }
        for (ZHtmlNode node:query().nodes().all().stream().iterable()){
            if (node instanceof ZHtmlElement){
                builder.append(((ZHtmlElement) node).getText());
                espace = false;
            } else if (node instanceof ZHtmlText) {
                if (espace) builder.append(" ");
                builder.append(((ZHtmlText)node).getText());
                espace = true;
            }
        }
        return builder.toString();
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
    
    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    public String getAttribute(String name) {
        return attributeMap.get(name);
    }
    
    public ZValue getAttrAsValue(String name){
        return ()->getAttribute(name);
    }
    
    public ZHtmlElement putAttribute(String name, String value){
        if (value==null) {
            removeAttribute(name);
        } else {
            attributeMap.put(name, value);
        }
        return this;
    }
    
    public ZHtmlElement removeAttribute(String name){
        attributeMap.remove(name);
        return this;
    }
    
    public boolean hasAttribute(String name){
        return attributeMap.containsKey(name);
    }
    
    public boolean hasClass(String className) {
        return listClassNames().contains(className);
    }
    
    public ZHtmlElement addClass(String className) {
        Objects.requireNonNull(className);
        ZList<String> classNameList = listClassNames();
        classNameList.add(className);
        String newClasses = listClassNames().stream()
                                            .reduce((a,b)->a+" "+b)
                                            .orElse(className);
        putAttribute("class", newClasses);
        return this;
    }
    
    public ZHtmlElement removeClass(String className) {
        String newClasses = listClassNames().stream()
                                            .filter(c->!c.equals(className))
                                            .reduce((a,b)->a+" "+b)
                                            .orElse(null);
        if (newClasses==null){
            removeAttribute("class");
        } else {
            putAttribute("class", newClasses);
        }
        return this;
    }
    
    public ZList<String> listClassNames(){
        String str = getAttrAsValue("class").asString().orElse("");
        String[] names = str.split(" ");
        return ZList.fromArray(names);
    }

    //==========================================================================
    //GETTERS E SETTERS MODIFICADOS
    //==========================================================================
    public String getId() {
        return getAttribute("id");
    }

    public ZValue getIdAsValue(){
        return ()->getAttribute("id");
    }
    
    public String getTagName() {
        return tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    
}
