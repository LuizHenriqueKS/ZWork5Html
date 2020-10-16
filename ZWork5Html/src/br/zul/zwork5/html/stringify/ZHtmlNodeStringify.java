package br.zul.zwork5.html.stringify;

import br.zul.zwork5.html.exception.ZHtmlNodeStringifyException;
import br.zul.zwork5.html.filter.ZHtmlNode;

/**
 *
 * @author luiz.silva
 */
public interface ZHtmlNodeStringify {
    
    String stringifyNode(ZHtmlNode node) throws ZHtmlNodeStringifyException;
    
}
