package cz.velim.bchw;


import java.io.*;
import java.util.*;

public class Main {

    static final String CBHW_DIR = "cbhw.dir";
    static List<InfoFile> allFiles = new ArrayList<InfoFile>();



    public static void main(String[] args) {

        InputStream input = null;
        Properties prop = new Properties();
        System.out.println("Start");

        try {

            input = new FileInputStream("config.properties");
            prop.load(input);
            String path = prop.getProperty(CBHW_DIR);

            if (null == path) {
                System.out.println("Directory not set.");
                return;
            }
            File dir = new File(path);

            for (File file : dir.listFiles()) {

                System.out.println(file.getAbsolutePath());
                SystemInfo m_system = new SystemInfo();
                ArrayList<Map<String, String>> m_software = new ArrayList<Map<String, String>>();
                HashMap<String, String> m_processes = new HashMap<String, String>();

                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line;
                while (((line = reader.readLine()) != null) && !(line.startsWith(DataSpec.SOFTWARE))) {

                    if (!isDataLine(line)) continue;

                    String[] vals = line.split(DataSpec.COLON);
                    if (vals[0].equals(DataSpec.HOST_NAME)) {
                        m_system.setHostName(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.IP_ADDRESS)) {
                        m_system.setIPAddress(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.USER_NAME)) {
                        m_system.setUserName(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.DESCRIPTION)) {
                        m_system.setDescription(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.OPERATING_SYSTEM)) {
                        m_system.addOperatingSystem(reader);
                    }
                    if (vals[0].equals(DataSpec.WINDOWS_PRODUCT_ID)) {
                        m_system.setWindowsProductID(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.WINDOWS_PRODUCT_KEY)) {
                        m_system.setWindowsProductKey(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.INTERNET_EXPLORER_VERSION)) {
                        m_system.setIEVersion(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.MODEL)) {
                        m_system.setModel(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.SYSTEM_TYPE)) {
                        m_system.setSystemType(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.BIOS)) {
                        m_system.setBIOS(reader);
                    }
                    if (vals[0].equals(DataSpec.SERIAL_NUMBER)) {
                        m_system.setSerialNumber(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.MOTHERBOARD)) {
                        m_system.setMotherboard(reader);
                    }
                    if (vals[0].equals(DataSpec.CHASSIS)) {
                        m_system.setChasis(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.PROCESSOR)) {
                        m_system.setProcessor(reader);
                    }
                    if (vals[0].equals(DataSpec.PHYSICAL_MEMORY)) {
                        m_system.setPhysicalMemory(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.MEMORY_SLOT)) {
                        m_system.setMemorySlot(reader);
                    }
                    if (vals[0].equals(DataSpec.DISK)) {
                        m_system.setDisk(reader);
                    }
                    if (vals[0].equals(DataSpec.LOGICAL_DRIVE)) {
                        m_system.setLogicalDrive(reader);
                    }
                    if (vals[0].equals(DataSpec.CD_ROM)) {
                        m_system.addCDROM(reader);
                    }
                    if (vals[0].equals(DataSpec.VIDEO)) {
                        m_system.addVideo(reader);
                    }
                    if (vals[0].equals(DataSpec.MONITOR)) {
                        m_system.addMonitor(reader);
                    }
                    if (vals[0].equals(DataSpec.PRINTER)) {
                        m_system.addPrinter(reader);
                    }
                    if (vals[0].equals(DataSpec.MULTIMEDIA)) {
                        m_system.addMultimedia(reader);
                    }
                    if (vals[0].equals(DataSpec.NETWORK_ADAPTER)) {
                        m_system.addNetworkAdapter(reader);
                    }
                    if (vals[0].equals(DataSpec.LOCAL_ACCOUNT)) {
                        m_system.addLocalAccount(reader);
                    }
                    if (vals[0].equals(DataSpec.SHARE)) {
                        m_system.addShare(reader);
                    }
                    if (vals[0].equals(DataSpec.SYSTEM_HOTFIX)) {
                        m_system.addSysHotfix(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.STARTUP)) {
                        m_system.addStartup(reader);
                    }
                }

                while (((line = reader.readLine()) != null) && !(line.startsWith(DataSpec.PROCESSES))) {
                    if (!isDataLine(line)) continue;
                    m_software.add(createAppMap(line));
                }

                while (((line = reader.readLine()) != null)) {
                    if (!isDataLine(line)) continue;
                    int pos = line.indexOf(DataSpec.SPACE);
                    m_processes.put(line.substring(pos + 1), line.substring(0, pos));
                }

                InfoFile infoFile = new InfoFile(m_system, m_software, m_processes);
                allFiles.add(infoFile);
        }
        System.out.println("consume the data");

        } catch (FileNotFoundException e) {
            System.out.println("Config file not found.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> createAppMap(String line) {
        Map m = new HashMap();
        String tmp = "Name:" + line;
        while (tmp.length() > 0) {
            int p0 = tmp.indexOf(DataSpec.COLON);
            int p1 = tmp.indexOf(DataSpec.COLON, p0 + 1);
            if (p1 > 0) {
                String t1 = tmp.substring(0, p1);
                int p2 = t1.lastIndexOf(DataSpec.COMMA);
                String item = t1.substring(0, p2);
                String[] vals = item.split(DataSpec.COLON);
                m.put(vals[0].trim(), vals[1].trim());
                tmp = tmp.substring(p2 + 1);
            } else {
                String[] vals = tmp.split(DataSpec.COLON);
                m.put(vals[0].trim(), vals[1].trim());
                tmp = "";
            }
        }

        return m;
    }

    private static boolean isDataLine(String line) {
        if (line.startsWith(DataSpec.SYSTEM) ||
                line.startsWith(DataSpec.SOFTWARE) ||
                line.startsWith(DataSpec.PROCESSES) ||
                line.startsWith(DataSpec.UNDER) ||
                line.length() == 0)
            return false;
        return true;
    }

}