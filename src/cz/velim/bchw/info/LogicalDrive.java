package cz.velim.bchw.info;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class LogicalDrive {
    private String name;
    private String description;
    private String size;
    private String freeSpace;
    private String fileSystem;
    private String serialNumber;
    private String networkPath;

    public LogicalDrive(BufferedReader reader) {
        try {
            this.name = reader.readLine().split(Constants.COLON)[1].trim();
            this.description = reader.readLine().split(Constants.COLON)[1].trim();
            this.size = reader.readLine().split(Constants.COLON)[1].trim();
            this.freeSpace = reader.readLine().split(Constants.COLON)[1].trim();
            this.fileSystem = reader.readLine().split(Constants.COLON)[1].trim();
            this.serialNumber = reader.readLine().split(Constants.COLON)[1].trim();
            this.networkPath = reader.readLine().split(Constants.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }

    public String getFreeSpace() {
        return freeSpace;
    }

    public String getFileSystem() {
        return fileSystem;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getNetworkPath() {
        return networkPath;
    }
}
