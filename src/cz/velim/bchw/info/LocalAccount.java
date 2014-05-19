package cz.velim.bchw.info;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class LocalAccount {
    private String caption;
    private String name;
    private String domain;
    private String description;
    private String SID;
    private String disabled;

    public LocalAccount(BufferedReader reader) {
        try {
            this.caption = reader.readLine().split(Constants.COLON)[1].trim();
            this.name = reader.readLine().split(Constants.COLON)[1].trim();
            this.domain =  reader.readLine().split(Constants.COLON)[1].trim();
            this.description = reader.readLine().split(Constants.COLON)[1].trim();
            this.SID  = reader.readLine().split(Constants.COLON)[1].trim();
            this.disabled = reader.readLine().split(Constants.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getCaption() {
        return caption;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public String getDescription() {
        return description;
    }

    public String getSID() {
        return SID;
    }

    public String getDisabled() {
        return disabled;
    }
}
