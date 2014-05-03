package cz.velim.bchw;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class CdRom {
    private String name;
    private String mediaType;
    private String manufacturer;
    private String drive;

    public CdRom(BufferedReader reader) {
        try {
            this.name = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.mediaType = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.manufacturer = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.drive = reader.readLine().split(DataSpec.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
