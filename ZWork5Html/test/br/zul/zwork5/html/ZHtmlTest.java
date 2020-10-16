
package br.zul.zwork5.html;

import br.zul.zwork5.exception.ZClosedException;
import br.zul.zwork5.html.exception.ZHtmlParseException;
import br.zul.zwork5.html.node.ZHtmlElement;
import br.zul.zwork5.io.ZResource;
import br.zul.zwork5.io.txt.ZTxtFileFastReader;
import br.zul.zwork5.str.ZStr;
import br.zul.zwork5.util.ZStrUtils;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luiz.silva
 */
public class ZHtmlTest {
        
    @Test
    public void testHomeHtml() throws IOException, ZClosedException, ZHtmlParseException {
        System.out.println("testHomeHtml");
        String source = new ZTxtFileFastReader(new ZResource(getClass(), "/resource/home.html")).readAll();
        ZHtml instance = new ZHtml(source);
        try {
            ZHtmlElement exp = instance.query()
                                       .elements()
                                       .all()
                                       .get(ZHtmlBy.id("exp"));
            String expText = new ZStr(exp.getText()).remove("\r", "\n")
                                                    .replaceRecursive("  ", " ")
                                                    .toString()
                                                    .trim();
            assertEquals("1 + 1 = 2", expText);
        } catch (NoSuchElementException ex){
            System.out.println(instance.toString());
        }
    }
    
    //@Test
    public void testWikiHtml() throws IOException, ZClosedException, ZHtmlParseException {
        System.out.println("testWikiHtml");
        String source = new ZTxtFileFastReader(new ZResource(getClass(), "/resource/wiki.html")).readAll();
        ZHtml instance = new ZHtml(source);
        System.out.println("listElements...");
        assertTrue(instance.query().elements().all().filter(ZHtmlBy.tagName("a")).anyMatch(this::isTagCidade));
    }
    
    private boolean isTagCidade(ZHtmlElement el){
        String a = ZStrUtils.normalize("Montenegro");
        String b = el.getAttribute("title");
        if (b==null) return false;
        b = ZStrUtils.normalize(b);
        boolean result = b.contains(a);
        if (result){
            System.out.println("::Achou");
        }
        return result;
    }
    
}
