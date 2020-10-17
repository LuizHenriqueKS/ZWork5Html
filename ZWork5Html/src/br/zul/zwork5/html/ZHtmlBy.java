package br.zul.zwork5.html;

import br.zul.zwork5.html.filter.ZHtmlElementFilter;
import br.zul.zwork5.util.ZStrUtils;
import java.util.Objects;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlBy {
    
    public static ZHtmlElementFilter id(String id){
        return (element)->{
            return Objects.equals(id, element.getId());
        };
    }
    
    public static ZHtmlElementFilter tagName(String tagName){
        return (element)->ZStrUtils.equalsIgnoreCase(element.getTagName(), tagName);
    }
    
    public static ZHtmlElementFilter className(String className){
        return (element)->element.hasClass(className);
    }
    
}
