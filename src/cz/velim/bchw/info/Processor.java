package cz.velim.bchw.info;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class Processor {

    private String name;
    private String manufacturer;
    private String maxClockSpeed;
    private String maxClockSpeedUnit;
    private String architecture;
    private String l2cache;
    private String l2cacheUnit;
    private String socket;
    private String version;

    public Processor(BufferedReader reader) {
        try {
            this.name = reader.readLine().split(Constants.COLON)[1].trim();
            this.manufacturer = reader.readLine().split(Constants.COLON)[1].trim();

            String tmp = reader.readLine().split(Constants.COLON)[1].trim();
            this.maxClockSpeed = tmp.split(Constants.SPACE)[0].trim();
            this.maxClockSpeedUnit = tmp.split(Constants.SPACE)[1].trim();

            this.architecture = reader.readLine().split(Constants.COLON)[1].trim();

            tmp = reader.readLine().split(Constants.COLON)[1].trim();
            if (tmp.trim().length() > 0) {
                this.l2cache = tmp.split(Constants.SPACE)[0].trim();
                this.l2cacheUnit = tmp.split(Constants.SPACE)[1].trim();
            }

            this.socket = reader.readLine().split(Constants.COLON)[1].trim();
            this.version = reader.readLine().split(Constants.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMaxClockSpeed() {
        return maxClockSpeed + Constants.SPACE + maxClockSpeedUnit;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getMaxClockSpeedUnit() {
        return maxClockSpeedUnit;
    }

    public String getArchitecture() {
        return architecture;
    }

    public String getL2cache() {
        return l2cache + Constants.SPACE + l2cacheUnit;
    }

    public String getL2cacheUnit() {
        return l2cacheUnit;
    }

    public String getSocket() {
        return socket;
    }

    public String getVersion() {
        return version;
    }
}
