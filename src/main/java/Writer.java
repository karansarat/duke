import java.io.*;
import java.nio.charset.*;


public class Writer {
    public PrintWriter out;
    public Writer(String filepath, boolean toAppend) throws IOException {
        try {
            this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath, toAppend), StandardCharsets.UTF_8)));
        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
    public void write(String content) {
        out.println(content);
        out.close();
    }
}

