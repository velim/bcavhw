package cz.velim.bchw;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class Startup {
    private String name = null;
    private String command = null;
    private String location = null;
    private String user = null;

    public Startup(BufferedReader reader) {
        try {
            name = reader.readLine().split(DataSpec.COLON)[1].trim();
            command = reader.readLine().split(DataSpec.COLON)[1].trim();
            location = reader.readLine().split(DataSpec.COLON)[1].trim();
            user = reader.readLine().split(DataSpec.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
