package cz.velim.bchw;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class Share {
    private String name;
    private String caption;
    private String path;
    private String type;

    public Share(BufferedReader reader) {
        try {
            this.name = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.caption = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.path = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.type = reader.readLine().split(DataSpec.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
