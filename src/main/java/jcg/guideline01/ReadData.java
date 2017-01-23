package jcg.guideline01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadData {
    /**
     * The String to hold the data is long-lived.  You could do the whole "prefer char[] array to
     * long-lived String" but now its deemed too hard for the programmer to remember to do this.
     * Where does it end with these people?  See compliant_readData.
     *
     * @throws IOException
     */
    public void nonCompliant_readData() throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream("file"))); // Read from the file
        // The String has a long life...
        String data = br.readLine();
    }

    /**
     * Leverage a directly allocated NIO buffer to read sensitive data from the file.  We are to
     * trust that "...It exists only in the system memory".  Its still a manual clear operation
     * to clean up the buffer.  This is begging to be a library function, no?
     *
     * @throws IOException
     */
    public void compliant_readData() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        try (FileChannel rdr = (new FileInputStream("file")).getChannel()) {
            while (rdr.read(buffer) > 0) {
                // Do something with the buffer
                buffer.clear();
            }
        } catch (Throwable e) { // Handle error
        }
    }
}
