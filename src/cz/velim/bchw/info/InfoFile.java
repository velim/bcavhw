package cz.velim.bchw.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mvelek on 5/3/2014.
 */
public class InfoFile {
    private final String m_path;
    private final String m_personelID;
    private SystemInfo m_system;
    private List<Map<String, String>> m_software;
    private Map<String, String> m_processes;

    public InfoFile(SystemInfo system, ArrayList<Map<String, String>> software, HashMap<String, String> processes, String path) {
        this.m_path = path;
        this.m_personelID = m_path.substring(0,m_path.indexOf(Constants.HYPHEN));
        this.m_system = system;
        this.m_software = software;
        this.m_processes = processes;
    }

    public String getPersonelID() {
        return m_personelID;
    }

    public SystemInfo getSystem() {
        return m_system;
    }

    public List<Map<String, String>> getSoftware() {
        return m_software;
    }

    public Map<String, String> getProcesses() {
        return m_processes;
    }

    public String getPath() {
        return m_path;
    }
}