package cz.velim.bchw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mvelek on 5/3/2014.
 */
public class InfoFile {
    SystemInfo system;
    List<Map<String, String>> software;
    Map<String, String> processes;

    public InfoFile(SystemInfo system, ArrayList<Map<String, String>> software, HashMap<String, String> processes){
        this.system = system;
        this.software = software;
        this.processes = processes;
    }
}
