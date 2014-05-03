package cz.velim.bchw;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class LogicalDrive {
    private String name;
    private String description;
    private String dize;
    private String freeSpace;
    private String fileSystem;
    private String serialNumber;
    private String networkPath;

    public LogicalDrive(BufferedReader reader) {
        try {
            this.name = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.description = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.dize = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.freeSpace = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.fileSystem = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.serialNumber = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.networkPath = reader.readLine().split(DataSpec.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
