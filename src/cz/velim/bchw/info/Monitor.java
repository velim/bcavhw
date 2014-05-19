package cz.velim.bchw.info;

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
    private String displaySize;
    private String manufactureDate;

    public Monitor(BufferedReader reader) {
        try {
            this.manufacturer = reader.readLine().split(Constants.COLON)[1].trim();
            this.name = reader.readLine().split(Constants.COLON)[1].trim();
            this.diagonal = reader.readLine().split(Constants.COLON)[1].trim();
            this.serialNumber = reader.readLine().split(Constants.COLON)[1].trim();
            this.maxResolution = reader.readLine().split(Constants.COLON)[1].trim();
            this.displaySize = reader.readLine().split(Constants.COLON)[1].trim();
            this.manufactureDate = reader.readLine().split(Constants.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }

    public String getDiagonal() {
        return diagonal;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getMaxResolution() {
        return maxResolution;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }
}
