package br.zul.zwork5.html.io;

import br.zul.zwork5.exception.ZClosedException;
import br.zul.zwork5.exception.ZFileAlreadyOpenException;
import br.zul.zwork5.html.ZHtml;
import br.zul.zwork5.io.ZFile;
import br.zul.zwork5.io.ZOSFile;
import br.zul.zwork5.io.txt.ZTxtFileFastWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author luizh
 */
public class ZHtmlFileWriter {
    
    public void write(ZFile file, ZHtml html) throws IOException, ZFileAlreadyOpenException, ZClosedException{
        String content = html.toString();
        new ZTxtFileFastWriter(file, false).writeAll(content);
    }
    
    public void write(File file, ZHtml html) throws IOException, ZFileAlreadyOpenException, ZClosedException{
        write(new ZOSFile(file), html);
    }
    
    public void write(String file, ZHtml html) throws IOException, ZFileAlreadyOpenException, ZClosedException{
        write(new ZOSFile(file), html);
    }
    
}
