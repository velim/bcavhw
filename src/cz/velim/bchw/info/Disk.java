package cz.velim.bchw.info;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class Disk {
    private String caption;
    private String size;
    private String manufacturer;
    private String interfaceType;
    private String mediaType;
    private String bytesPerSector;
    private String heads;
    private String cylinders;
    private String tracks;
    private String sectors;

    public Disk(BufferedReader reader) {
        try {
            this.caption = reader.readLine().split(Constants.COLON)[1].trim();
            this.size = reader.readLine().split(Constants.COLON)[1].trim();
            this.manufacturer = reader.readLine().split(Constants.COLON)[1].trim();
            this.interfaceType = reader.readLine().split(Constants.COLON)[1].trim();
            this.mediaType = reader.readLine().split(Constants.COLON)[1].trim();
            this.bytesPerSector = reader.readLine().split(Constants.COLON)[1].trim();
            this.heads = reader.readLine().split(Constants.COLON)[1].trim();
            this.cylinders = reader.readLine().split(Constants.COLON)[1].trim();
            this.sectors = reader.readLine().split(Constants.COLON)[1].trim();
            this.tracks = reader.readLine().split(Constants.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCaption() {
        return caption;
    }

    public String getSize() {
        return size;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getBytesPerSector() {
        return bytesPerSector;
    }

    public String getHeads() {
        return heads;
    }

    public String getCylinders() {
        return cylinders;
    }

    public String getTracks() {
        return tracks;
    }

    public String getSectors() {
        return sectors;
    }
}
