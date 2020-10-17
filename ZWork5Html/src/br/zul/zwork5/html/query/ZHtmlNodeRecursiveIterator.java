package br.zul.zwork5.html.query;

import br.zul.zwork5.html.filter.ZHtmlNode;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.stream.Stream;

/**
 *
 * @author luiz.silva
 * @param <T>
 */
public class ZHtmlNodeRecursiveIterator<T extends ZHtmlNode> implements Iterator<T> {
    
    //==========================================================================
    //EXCEPTION
    //==========================================================================
    private static class NoNextIteratorException extends Exception{}
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private final Queue<Iterator<? extends ZHtmlNode>> iteratorQueue;
    private final Class<T> cls;
    
    private Iterator<? extends ZHtmlNode> currentIterator;
    private T currentItem;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public ZHtmlNodeRecursiveIterator(Stream<T> stream, Class<T> cls){
        this.cls = cls;
        iteratorQueue = new LinkedList<>();
        iteratorQueue.add(stream.iterator());
        nextItem();
    }
    
    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    @Override
    public boolean hasNext() {
        return currentItem!=null;
    }

    @Override
    public T next() {
        T result = currentItem;
        nextItem();
        return result;
    }
    
    //==========================================================================
    //MÉTODOS PRIVADOS
    //==========================================================================
    private void nextItem(){
        try {
            currentItem = tryGetNextItem();
            if (isValidItem(currentItem)){
                iteratorQueue.add(currentItem.listChildren().iterator());
            } else {
                nextItem();
            }
        } catch (NoNextIteratorException ex){
            currentItem = null;
        }
    }
    
    private T tryGetNextItem() throws NoNextIteratorException {
        try {
            return (T)getIterator().next();
        } catch (NoSuchElementException ex){
            nextIterator();
            return null;
        }
    }
    
    private Iterator<? extends ZHtmlNode> getIterator() throws NoNextIteratorException{
        if (currentIterator==null){
            nextIterator();
            return getIterator();
        } else {
            return currentIterator;
        }
    }

    private void nextIterator() throws NoNextIteratorException {
        if (iteratorQueue.isEmpty()){
            throw new NoNextIteratorException();
        } else {
            currentIterator = iteratorQueue.remove();
        }
    }
    
    private boolean isValidItem(T item) {
        if (item==null) {
            return false;
        } else {
            return cls.isAssignableFrom(item.getClass());
        }
    }
    
}
