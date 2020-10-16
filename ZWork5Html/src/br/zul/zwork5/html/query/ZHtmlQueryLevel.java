package br.zul.zwork5.html.query;

import br.zul.zwork5.stream.ZStream;
import br.zul.zwork5.util.ZList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlQueryLevel<T> {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final Stream<T> stream;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlQueryLevel(Stream<T> stream) {
        this.stream = stream;
    }
    
    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    public Optional<T> first(Predicate<T> filter) throws NoSuchElementException{
        return stream.filter(filter).findFirst();
    }
    
    public Optional<T> last(Predicate<T> filter) throws NoSuchElementException{
        return filter(filter).findLast();
    }
    
    public ZStream<T> stream() {
        return new ZStream<>(stream);
    }
    
    public ZStream<T> filter(Predicate<T> filter){
        return stream().filter(filter);
    }

    public T get(Predicate<T> filter) throws NoSuchElementException {
        return first(filter).get();
    }
    
    public ZList<T> list(){
        return stream().toList();
    }
    
    public ZList<T> list(Predicate<T> filter){
        return filter(filter).toList();
    }
    
}
