package cz.velim.bchw;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class OperatingSystem {
    private String name;
    private String version;
    private String build;
    private String installDate;

    public OperatingSystem(BufferedReader reader) {
        try {
            this.name = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.version = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.build = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.installDate = reader.readLine().split(DataSpec.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
