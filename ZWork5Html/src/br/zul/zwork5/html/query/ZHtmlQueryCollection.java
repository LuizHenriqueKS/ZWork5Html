package br.zul.zwork5.html.query;

import br.zul.zwork5.html.filter.ZHtmlNode;
import br.zul.zwork5.util.ZStreamUtils;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author luiz.silva
 * @param <T>
 */
public class ZHtmlQueryCollection<T extends ZHtmlNode> {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final Stream<T> stream;
    private final Class<T> cls;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlQueryCollection(List<? extends ZHtmlNode> collection, Class<T> cls) {
        this.stream = collection.stream()
                                .filter(n->cls.isAssignableFrom(n.getClass()))
                                .map(n->(T)n);
        this.cls = cls;
    }
    
    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    public ZHtmlQueryLevel<T> children(){
        return new ZHtmlQueryLevel<>(stream);
    }
    
    public ZHtmlQueryLevel<T> all(){
        Iterator<T> iterator = new ZHtmlNodeRecursiveIterator<>(stream, cls);
        Stream<T> localStream = ZStreamUtils.fromIterator(iterator);
        return new ZHtmlQueryLevel<>(localStream);
    }
    
}
