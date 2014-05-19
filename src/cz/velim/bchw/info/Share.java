package cz.velim.bchw.info;

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
            this.name = reader.readLine().split(Constants.COLON)[1].trim();
            this.caption = reader.readLine().split(Constants.COLON)[1].trim();
            this.path = reader.readLine().split(Constants.COLON)[1].trim();
            this.type = reader.readLine().split(Constants.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }
}
