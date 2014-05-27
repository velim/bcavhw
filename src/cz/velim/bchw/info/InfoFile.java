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
    private final String m_personalID;
    private SystemInfo m_system;
    private List<Map<String, String>> m_software;
    private Map<String, String> m_processes;
    private String m_personalName;

    private int m_PCOrder;

    public InfoFile(SystemInfo system, ArrayList<Map<String, String>> software, HashMap<String, String> processes, String path, Map<String, String> pList) {
        this.m_path = path;
        this.m_personalID = m_path.substring(0, m_path.indexOf(Constants.HYPHEN));
        this.m_personalName = pList.get(m_personalID);
        this.m_PCOrder = 1;
        this.m_system = system;
        this.m_software = software;
        this.m_processes = processes;
    }

    public String getPersonalID() {
        return m_personalID;
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

    public String getPersonalName() {
        return m_personalName;
    }

    public int getPCOrder() {
        return m_PCOrder;
    }

    public void updatePCOrder(InfoFile iFile) {
        if (null != iFile)
            if (this.m_personalID.equals(iFile.getPersonalID())) {
                this.m_PCOrder = iFile.getPCOrder() + 1;
            }
    }


}