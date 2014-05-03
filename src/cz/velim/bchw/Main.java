package cz.velim.bchw;


import java.io.*;
import java.util.*;

public class Main {

    static final String CBHW_DIR = "cbhw.dir";
    static SystemData system;
    static List<Map<String, String>> software;
    static Map<String, String> processes;


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
                BufferedReader reader = new BufferedReader(new FileReader(file));

                system = new SystemData();
                software = new ArrayList<Map<String, String>>();
                processes = new HashMap<String, String>();

                String line;
                while (((line = reader.readLine()) != null) && !(line.startsWith(DataSpec.SOFTWARE))) {

                    if (!isDataLine(line)) continue;

                    String[] vals = line.split(DataSpec.COLON);
                    if (vals[0].equals(DataSpec.HOST_NAME)) {
                        system.setHostName(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.IP_ADDRESS)) {
                        system.setIPAddress(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.USER_NAME)) {
                        system.setUserName(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.DESCRIPTION)) {
                        system.setDescription(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.OPERATING_SYSTEM)) {
                        system.addOperatingSystem(reader);
                    }
                    if (vals[0].equals(DataSpec.WINDOWS_PRODUCT_ID)) {
                        system.setWindowsProductID(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.WINDOWS_PRODUCT_KEY)) {
                        system.setWindowsProductKey(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.INTERNET_EXPLORER_VERSION)) {
                        system.setIEVersion(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.MODEL)) {
                        system.setModel(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.SYSTEM_TYPE)) {
                        system.setSystemType(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.BIOS)) {
                        system.setBIOS(reader);
                    }
                    if (vals[0].equals(DataSpec.SERIAL_NUMBER)) {
                        system.setSerialNumber(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.MOTHERBOARD)) {
                        system.setMotherboard(reader);
                    }
                    if (vals[0].equals(DataSpec.CHASSIS)) {
                        system.setChasis(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.PROCESSOR)) {
                        system.setProcessor(reader);
                    }
                    if (vals[0].equals(DataSpec.PHYSICAL_MEMORY)) {
                        system.setPhysicalMemory(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.MEMORY_SLOT)) {
                        system.setMemorySlot(reader);
                    }
                    if (vals[0].equals(DataSpec.DISK)) {
                        system.setDisk(reader);
                    }
                    if (vals[0].equals(DataSpec.LOGICAL_DRIVE)) {
                        system.setLogicalDrive(reader);
                    }
                    if (vals[0].equals(DataSpec.CD_ROM)) {
                        system.addCDROM(reader);
                    }
                    if (vals[0].equals(DataSpec.VIDEO)) {
                        system.addVideo(reader);
                    }
                    if (vals[0].equals(DataSpec.MONITOR)) {
                        system.addMonitor(reader);
                    }
                    if (vals[0].equals(DataSpec.PRINTER)) {
                        system.addPrinter(reader);
                    }
                    if (vals[0].equals(DataSpec.MULTIMEDIA)) {
                        system.addMultimedia(reader);
                    }
                    if (vals[0].equals(DataSpec.NETWORK_ADAPTER)) {
                        system.addNetworkAdapter(reader);
                    }
                    if (vals[0].equals(DataSpec.LOCAL_ACCOUNT)) {
                        system.addLocalAccount(reader);
                    }
                    if (vals[0].equals(DataSpec.SHARE)) {
                        system.addShare(reader);
                    }
                    if (vals[0].equals(DataSpec.SYSTEM_HOTFIX)) {
                        system.addSysHotfix(vals[1].trim());
                    }
                    if (vals[0].equals(DataSpec.STARTUP)) {
                        system.addStartup(reader);
                    }
                }

                while (((line = reader.readLine()) != null) && !(line.startsWith(DataSpec.PROCESSES))) {
                    if (!isDataLine(line)) continue;
                    software.add(getAppMap(line));
                }

                while (((line = reader.readLine()) != null)) {
                    if (!isDataLine(line)) continue;
                    int pos = line.indexOf(DataSpec.SPACE);
                    processes.put(line.substring(pos + 1), line.substring(0, pos));
                }
                System.out.println(system.getStartups().size());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Config file not found.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> getAppMap(String line) {
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