package cz.velim.bchw;

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
            this.name = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.currentHorizontalResolution = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.currentVerticalResolution = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.currentBitsPerPixel = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.currentRefreshRate = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.memorySize = reader.readLine().split(DataSpec.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
