package cz.velim.bchw.info;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class Motherboard {
    private String manufacturer;
    private String product;
    private String version;

    public Motherboard(BufferedReader reader) {
        try {
            this.manufacturer = reader.readLine().split(Constants.COLON)[1].trim();
            this.product = reader.readLine().split(Constants.COLON)[1].trim();
            this.version = reader.readLine().split(Constants.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getProduct() {
        return product;
    }

    public String getVersion() {
        return version;
    }
}
