package br.zul.zwork5.html.parser.instruction;

import br.zul.zwork5.html.node.ZHtmlElement;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstruction;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionData;
import br.zul.zwork5.html.parser.ZHtmlNodeParserInstructionRunner;
import br.zul.zwork5.html.parser.ZHtmlNodeTreeBuilder;
import br.zul.zwork5.str.ZStr;
import br.zul.zwork5.str.search.ZStrSearchResult;
import br.zul.zwork5.util.ZPair;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author luiz.silva
 */
class ZHtmlNodeParserInstructionOpenElement implements ZHtmlNodeParserInstruction {

    //==========================================================================
    //CONSTANTES
    //==========================================================================
    private final static List<String> QUOTES = Arrays.asList("'", "\"");
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final ZStr source;
    private final ZHtmlNodeParserInstructionData data;
    
    private String tagName;
    
    private boolean hasChildren;
    private boolean closed;
    
    private Map<String, String> attributeMap;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeParserInstructionOpenElement(ZStr source, ZHtmlNodeParserInstructionData data) {
        this.data = data;
        source = source.trim();
        if (source.endsWith("/")){
            closed = true;
            this.source = source.till(source.length()-1);
        }  else {
            this.source = source;
        }
    }

    //==========================================================================
    //MÉTODOS PÚBLICOS SOBRESCRITOS
    //==========================================================================
    @Override
    public void preRun(ZHtmlNodeParserInstructionRunner runner) {
        if (!closed){
            runner.addAlias("element:"+getTagName(), this);
        }
    }

    @Override
    public void run(ZHtmlNodeTreeBuilder treeBuilder) {
        ZHtmlElement element = new ZHtmlElement();
        element.setTagName(tagName);
        getAttributeMap().forEach(element::putAttribute);
        treeBuilder.addElementWithChildren(element);
    }
    
    //==========================================================================
    //MÉTODOS PRIVADOS
    //==========================================================================
    private String getTagName() {
        if (tagName==null){
            tagName = source.till(" ", "\t").toString();
        }
        return tagName;
    }

    private Map<String, String> getAttributeMap() {
        if (attributeMap==null){
            attributeMap = new LinkedHashMap<>();
            if (source.containsAny(" ")){
                fillAttributeRecursive(attributeMap, source.from(" "));
            }
        }
        return attributeMap;
    }

    private void fillAttributeRecursive(Map<String, String> attributeMap, ZStr str) {
        str = str.trim();
        if (!str.isEmpty()){
            ZStrSearchResult equalsPosition = str.search("=").next();
            if (equalsPosition==null){
                String key = str.toString();
                String value = "";
                attributeMap.put(key, value);
            } else {
                String key = str.till(equalsPosition.getStartIndex()).toString();
                str = str.from(equalsPosition.getEndIndex()).trim();
                if (hasQuote(str)){
                    ZPair<Integer, Integer> indices = indicesOfQuotes(str);
                    str = str.from(indices.getA());
                    String value = str.till(indices.getB()).toString();
                    attributeMap.put(key, value);
                    str = str.from(indices.getB()+1);
                } else {
                    ZStrSearchResult endResult = str.search(" ", "\t").next();
                    Integer end;
                    if (endResult==null){
                        end = str.length();
                    } else {
                        end = endResult.getStartIndex();
                    }
                    String value = str.till(end).toString();
                    str = str.from(end+1);
                    attributeMap.put(key, value);
                }
                fillAttributeRecursive(attributeMap, str);
            }
        }
    }

    private boolean hasQuote(ZStr str) {
        return QUOTES.contains(str.substr(0, 1).toString());
    }
    
    private ZPair<Integer, Integer> indicesOfQuotes(ZStr str) {
        String quote = str.substr(0, 1).toString();
        ZStrSearchResult searchResult = str.substr(1).search(quote).next();
        Integer start = 1;
        Integer end;
        if (searchResult==null){
            end = str.length(); 
        } else {
            end = searchResult.getEndIndex();
        }
        return new ZPair<>(start, end-1);
    }
    
    //==========================================================================
    //GETTERS E SETTERS
    //==========================================================================
    public boolean hasChildren() {
        return hasChildren;
    }
    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean isClosed() {
        return closed;
    }
    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    
}
