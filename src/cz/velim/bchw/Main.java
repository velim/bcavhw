package cz.velim.bchw;


import cz.velim.bchw.db.DBWriter;
import cz.velim.bchw.db.MyConnection;
import cz.velim.bchw.exceptions.DuplicateValue;
import cz.velim.bchw.info.Constants;
import cz.velim.bchw.info.InfoFile;
import cz.velim.bchw.info.SystemInfo;

import java.io.*;
import java.util.*;

public class Main {

    static final String CBHW_DIR = "cbhw.dir";
    static List<InfoFile> allFiles = new ArrayList<InfoFile>();


    public static void main(String[] args) {

        InputStream input;
        Properties prop;
        String path = "";

        if (args.length < 1) {
            System.out.println("Config file not specified!");
            return;
        }


        try {

            input = new FileInputStream(args[0]);
            prop = new Properties();
            prop.load(input);
            path = prop.getProperty(CBHW_DIR);

            if (null == path) {
                System.out.println("Directory not set.");
                return;
            }
            File dir = new File(path);

            for (File file : dir.listFiles()) {

                //System.out.println(file.getAbsolutePath());
                SystemInfo m_system = new SystemInfo();
                ArrayList<Map<String, String>> m_software = new ArrayList<Map<String, String>>();
                HashMap<String, String> m_processes = new HashMap<String, String>();

                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line;
                while (((line = reader.readLine()) != null) && !(line.startsWith(Constants.SOFTWARE))) {

                    if (!isDataLine(line)) continue;

                    String[] vals = line.split(Constants.COLON);
                    if (vals[0].equals(Constants.HOST_NAME)) {
                        m_system.setHostName(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.IP_ADDRESS)) {
                        m_system.setIPAddress(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.USER_NAME)) {
                        m_system.setUserName(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.DESCRIPTION)) {
                        m_system.setDescription(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.OPERATING_SYSTEM)) {
                        m_system.addOperatingSystem(reader);
                    }
                    if (vals[0].equals(Constants.SERVICE_PACK)) {
                        m_system.setServicePack(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.WINDOWS_PRODUCT_ID)) {
                        m_system.setWindowsProductID(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.WINDOWS_PRODUCT_KEY)) {
                        m_system.setWindowsProductKey(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.INTERNET_EXPLORER_VERSION)) {
                        m_system.setIEVersion(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.MODEL)) {
                        m_system.setModel(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.SYSTEM_TYPE)) {
                        m_system.setSystemType(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.BIOS)) {
                        m_system.setBIOS(reader);
                    }
                    if (vals[0].equals(Constants.SERIAL_NUMBER)) {
                        m_system.setSerialNumber(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.MOTHERBOARD)) {
                        m_system.setMotherboard(reader);
                    }
                    if (vals[0].equals(Constants.CHASSIS)) {
                        m_system.setChasis(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.PROCESSOR)) {
                        m_system.setProcessor(reader);
                    }
                    if (vals[0].equals(Constants.PHYSICAL_MEMORY)) {
                        m_system.setPhysicalMemory(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.MEMORY_SLOT)) {
                        m_system.addMemorySlot(reader);
                    }
                    if (vals[0].equals(Constants.DISK)) {
                        m_system.setDisk(reader);
                    }
                    if (vals[0].equals(Constants.LOGICAL_DRIVE)) {
                        m_system.setLogicalDrive(reader);
                    }
                    if (vals[0].equals(Constants.CD_ROM)) {
                        m_system.addCDROM(reader);
                    }
                    if (vals[0].equals(Constants.VIDEO)) {
                        m_system.addVideo(reader);
                    }
                    if (vals[0].equals(Constants.MONITOR)) {
                        m_system.addMonitor(reader);
                    }
                    if (vals[0].equals(Constants.PRINTER)) {
                        m_system.addPrinter(reader);
                    }
                    if (vals[0].equals(Constants.MULTIMEDIA)) {
                        m_system.addMultimedia(reader);
                    }
                    if (vals[0].equals(Constants.NETWORK_ADAPTER)) {
                        m_system.addNetworkAdapter(reader);
                    }
                    if (vals[0].equals(Constants.LOCAL_ACCOUNT)) {
                        m_system.addLocalAccount(reader);
                    }
                    if (vals[0].equals(Constants.SHARE)) {
                        m_system.addShare(reader);
                    }
                    if (vals[0].equals(Constants.SYSTEM_HOTFIX)) {
                        m_system.addSysHotfix(vals[1].trim());
                    }
                    if (vals[0].equals(Constants.STARTUP)) {
                        m_system.addStartup(reader);
                    }
                }

                while (((line = reader.readLine()) != null) && !(line.startsWith(Constants.PROCESSES))) {
                    if (!isDataLine(line)) continue;
                    m_software.add(createAppMap(line));
                }

                while (((line = reader.readLine()) != null)) {
                    if (!isDataLine(line)) continue;
                    int pos = line.indexOf(Constants.SPACE);
                    m_processes.put(line.substring(pos + 1), line.substring(0, pos));
                }

                InfoFile infoFile = new InfoFile(m_system, m_software, m_processes, file.getName());
                allFiles.add(infoFile);
            }

            System.out.println("consume the data");
            // test connect to DB
            // http://www.programcreek.com/2010/05/java-code-for-connecting-ms-sql-server-by-using-sql-server-authentication/

            Collections.sort(allFiles,new Comparator<InfoFile>() {
                @Override
                public int compare(InfoFile o1, InfoFile o2) {
                    return o1.getPersonelID().compareTo(o2.getPersonelID());
                }
            });
            MyConnection conn = new MyConnection();
            if (conn.initConnection(prop.getProperty(Constants.CBHW_DB_STRING))) {
                DBWriter dbwriter = new DBWriter(conn.getConnection());
                dbwriter.writeData(allFiles);
            }


        } catch (FileNotFoundException e) {
            System.out.println("Config file not found.");

        } catch (IOException e) {
            System.err.println(path);
            e.printStackTrace();
        } catch (DuplicateValue e) {
            System.err.println(path);
            e.printStackTrace();
        }

    }
    private static Map<String, String> createAppMap (String line){
            Map m = new HashMap();
            String tmp = "Name:" + line;
            String[] entries = tmp.split(Constants.COMMA);
            String lastkey = "";
            for (String entry : entries){
                String[] vals = entry.split(Constants.COLON);
                try{
                    m.put(vals[0].trim(), vals[1].trim());
                    lastkey = vals[0].trim();
                }catch (ArrayIndexOutOfBoundsException e){
                    m.put(lastkey, m.get(lastkey)+Constants.COMMA +vals[0]);
                }
            }

            return m;
        }

    private static boolean isDataLine(String line) {
        if (line.startsWith(Constants.SYSTEM) ||
                line.startsWith(Constants.SOFTWARE) ||
                line.startsWith(Constants.PROCESSES) ||
                line.startsWith(Constants.UNDER) ||
                line.length() == 0)
            return false;
        return true;
    }

}