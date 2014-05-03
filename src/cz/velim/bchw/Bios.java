package cz.velim.bchw;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class Bios {
    private String manufacturer;
    private String version;
    private String date;

    public Bios(BufferedReader reader) {
        try {
            this.manufacturer = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.version = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.date = reader.readLine().split(DataSpec.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
