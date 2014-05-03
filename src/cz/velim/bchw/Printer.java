package cz.velim.bchw;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class Printer {
    private String field1;
    private String field2;
    private String field3;
    private String field4;

    public Printer(BufferedReader reader) {
        try {
            this.field1 = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.field2 = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.field3 = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.field4 = reader.readLine().split(DataSpec.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
