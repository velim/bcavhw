package cz.velim.bchw.info;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class Video {
    private String name;
    private String currentHorizontalResolution;
    private String currentVerticalResolution;
    private String currentBitsPerPixel;
    private String currentRefreshRate;
    private String memorySize;

    public Video(BufferedReader reader) {
        try {
            this.name = reader.readLine().split(Constants.COLON)[1].trim();
            this.currentHorizontalResolution = reader.readLine().split(Constants.COLON)[1].trim();
            this.currentVerticalResolution = reader.readLine().split(Constants.COLON)[1].trim();
            this.currentBitsPerPixel = reader.readLine().split(Constants.COLON)[1].trim();
            this.currentRefreshRate = reader.readLine().split(Constants.COLON)[1].trim();
            this.memorySize = reader.readLine().split(Constants.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getCurrentHorizontalResolution() {
        return currentHorizontalResolution;
    }

    public String getCurrentVerticalResolution() {
        return currentVerticalResolution;
    }

    public String getCurrentBitsPerPixel() {
        return currentBitsPerPixel;
    }

    public String getCurrentRefreshRate() {
        return currentRefreshRate;
    }

    public String getMemorySize() {
        return memorySize;
    }
}
