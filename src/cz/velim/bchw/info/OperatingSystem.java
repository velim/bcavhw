package cz.velim.bchw.info;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class OperatingSystem {
    private String name;
    private String version;
    private String build;
    private String installDate;

    public OperatingSystem(BufferedReader reader) {
        try {
            this.name = reader.readLine().split(Constants.COLON)[1].trim();
            this.version = reader.readLine().split(Constants.COLON)[1].trim();
            this.build = reader.readLine().split(Constants.COLON)[1].trim();
            this.installDate = parseInstallDate(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseInstallDate(String s) {
        int pos = s.indexOf(Constants.COLON);
        return s.substring(pos+1).trim();
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getBuild() {
        return build;
    }

    public String getInstallDate() {
        return installDate;
    }
}
