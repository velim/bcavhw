package cz.velim.bchw;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class Processor{
    private String name;
    private String manufacturer;
    private String maxClockSpeed;
    private String architecture;
    private String l2cache;
    private String socket;
    private String version;

    public Processor(BufferedReader reader) {
        try {
            this.name = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.manufacturer = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.maxClockSpeed = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.architecture = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.l2cache = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.socket = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.version = reader.readLine().split(DataSpec.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
