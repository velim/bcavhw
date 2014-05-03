package cz.velim.bchw;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class MemorySlot {
    private String capacity;
    private String deviceLocator;
    private String bankLabel;
    private String formFactor;
    private String memoryType;
    private String manufacturer;
    private String speed;
    private String maxCapacity;

    public MemorySlot(BufferedReader reader) {
        try {
            this.capacity = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.deviceLocator = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.bankLabel = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.formFactor = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.memoryType = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.manufacturer = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.speed = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.maxCapacity = reader.readLine().split(DataSpec.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
