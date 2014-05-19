package cz.velim.bchw.info;

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
            this.field1 = reader.readLine().split(Constants.COLON)[1].trim();
            this.field2 = reader.readLine().split(Constants.COLON)[1].trim();
            this.field3 = reader.readLine().split(Constants.COLON)[1].trim();
            this.field4 = reader.readLine().split(Constants.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    public String getField4() {
        return field4;
    }
}
