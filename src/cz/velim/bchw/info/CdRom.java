package cz.velim.bchw.info;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class CdRom {
    private String name;
    private String mediaType;
    private String manufacturer;
    private String networkPath;

    public CdRom(BufferedReader reader) {
        try {
            this.name = reader.readLine().split(Constants.COLON)[1].trim();
            this.mediaType = reader.readLine().split(Constants.COLON)[1].trim();
            this.manufacturer = reader.readLine().split(Constants.COLON)[1].trim();
            this.networkPath = reader.readLine().split(Constants.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getNetworkPath() {
        return networkPath;
    }
}
