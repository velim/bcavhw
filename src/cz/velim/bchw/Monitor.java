package cz.velim.bchw;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class Monitor {

    private String manufacturer;
    private String name;
    private String diagonal;
    private String serialNumber;
    private String maxResolution;
    private String dispplaySize;
    private String manufactureDate;

    public Monitor(BufferedReader reader) {
        try {
            this.manufacturer = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.name = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.diagonal = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.serialNumber = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.maxResolution = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.dispplaySize = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.manufactureDate = reader.readLine().split(DataSpec.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
