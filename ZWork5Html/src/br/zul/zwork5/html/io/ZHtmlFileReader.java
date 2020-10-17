package br.zul.zwork5.html.io;

import br.zul.zwork5.exception.ZClosedException;
import br.zul.zwork5.html.ZHtml;
import br.zul.zwork5.html.exception.ZHtmlParseException;
import br.zul.zwork5.io.ZFile;
import br.zul.zwork5.io.ZOSFile;
import br.zul.zwork5.io.txt.ZTxtFileFastReader;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author luizh
 */
public class ZHtmlFileReader {
    
    public ZHtml read(ZFile file) throws IOException, ZClosedException, ZHtmlParseException{
        String content = new ZTxtFileFastReader(file).readAll();
        return new ZHtml(content);
    }
    
    public ZHtml read(File file) throws IOException, ZClosedException, ZHtmlParseException{
        return read(new ZOSFile(file));
    }
    
    public ZHtml read(String file) throws IOException, ZClosedException, ZHtmlParseException{
        return read(new ZOSFile(file));
    }
    
}
