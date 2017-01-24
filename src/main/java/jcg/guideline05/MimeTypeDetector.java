package jcg.guideline05;

import java.io.File;
import java.io.IOException;

public interface MimeTypeDetector {
    String detectMimeType(File file)  throws IOException;
}
