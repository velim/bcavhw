package cz.velim.bchw.info;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class Multimedia {
    private String name;
    private String manufacturer;

    public Multimedia(BufferedReader reader) {
        try {
            this.name = reader.readLine().split(Constants.COLON)[1].trim();
            this.manufacturer = reader.readLine().split(Constants.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
