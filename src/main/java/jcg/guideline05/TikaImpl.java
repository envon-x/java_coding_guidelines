package jcg.guideline05;

import java.io.File;
import java.io.IOException;

import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeTypes;

public class TikaImpl implements MimeTypeDetector {

    private static final Detector DETECTOR = new DefaultDetector(MimeTypes.getDefaultMimeTypes());

    @Override
    public String detectMimeType(File file) throws IOException {
        TikaInputStream tikaIS = null;
        try {
            tikaIS = TikaInputStream.get(file);
            /*
             * You might not want to provide the file's name. If you provide an Excel
             * document with a .xls extension, it will get it correct right away; but
             * if you provide an Excel document with .doc extension, it will guess it
             * to be a Word document
             */
            final Metadata metadata = new Metadata();
            // metadata.set(Metadata.RESOURCE_NAME_KEY, file.getName());

            return DETECTOR.detect(tikaIS, metadata).toString();
        } finally {
            if (tikaIS != null) {
                tikaIS.close();
            }
        }
    }
}
