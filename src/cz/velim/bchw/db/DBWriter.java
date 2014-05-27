package cz.velim.bchw.db;

import cz.velim.bchw.info.*;

import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by mvelek on 5/6/2014.
 */
public class DBWriter {
    public static final String INSERT_HOST_REPORT = "INSERT INTO dbo.HOST_REPORT VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String SELECT_HOST_REPORT_ID = "SELECT MAX(ID) FROM HOST_REPORT";
    public static final String INSERT_SYSTEM = "INSERT INTO OPERATING_SYSTEM VALUES (?,?,?,?,?)";
    public static final String INSERT_BIOS = "INSERT INTO BIOS VALUES (?,?,?,?)";
    public static final String INSERT_MOTHERBOARD = "INSERT INTO MOTHERBOARD VALUES (?,?,?,?)";
    public static final String INSERT_MONITOR = "INSERT INTO MONITOR VALUES (?,?,?,?,?,?,?,?)";
    public static final String INSERT_LOCAL_ACCOUNT = "INSERT INTO LOCAL_ACCOUNT VALUES (?,?,?,?,?,?,?)";
    public static final String INSERT_STARTUP = "INSERT INTO STARTUP VALUES (?,?,?,?,?)";
    public static final String INSERT_PROCESSOR = "INSERT INTO PROCESSOR VALUES (?,?,?,?,?,?,?)";
    public static final String INSERT_MEMORY_SLOT = "INSERT INTO MEMORY_SLOT VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String INSERT_DISK = "INSERT INTO DISK VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    public static final String INSERT_LOGICAL_DRIVE = "INSERT INTO LOGICAL_DRIVE VALUES (?,?,?,?,?,?,?,?)";
    public static final String INSERT_CDROM = "INSERT INTO CDROM VALUES (?,?,?,?,?)";
    public static final String INSERT_VIDEO = "INSERT INTO VIDEO VALUES (?,?,?,?,?,?)";
    public static final String INSERT_PRINTER = "INSERT INTO PRINTER VALUES (?,?,?,?,?)";
    public static final String INSERT_MULTIMEDIA = "INSERT INTO MULTIMEDIA VALUES (?,?,?)";
    public static final String INSERT_NETWORK_ADAPTER = "INSERT INTO NETWORK_ADAPTER VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String INSERT_SHARE = "INSERT INTO SHARE VALUES (?,?,?,?,?)";
    public static final String INSERT_HOTFIX = "INSERT INTO HOTFIX VALUES (?,?)";
    public static final String INSERT_SOFTWARE = "INSERT INTO SOFTWARE VALUES (?,?,?,?,?,?)";


    private final Connection conn;
    private String lastId;

    public DBWriter(Connection connection) {
        this.conn = connection;
    }

    public void writeData(List<InfoFile> allFiles) {
        for (InfoFile iFile : allFiles) {
            try {
                writeFileInfo(iFile);
            } catch (SQLException e) {
                //TODO - log the error message
                System.err.println(iFile.getPath());
                e.printStackTrace();

            } catch (Exception e) {
                //TODO - log the error message
                System.err.println(iFile.getPath());
                e.printStackTrace();
            }
        }
    }

    private Boolean writeFileInfo(InfoFile iFile) throws SQLException {
        writeHostReport(iFile);
        this.lastId = getLastId();

        writeOperatingSystem(iFile);
        writeBIOS(iFile);
        writeMotherboard(iFile);
        writeMonitors(iFile);
        writeLocalaccounts(iFile);
        writeStartups(iFile);
        writeProcessors(iFile);
        writeSlots(iFile);
        writeDisks(iFile);
        writeLogicalDrives(iFile);
        writeCdRoms(iFile);
        writeVideos(iFile);
        writePrinters(iFile);
        writeMultimedias(iFile);
        writeNetwoekAdapters(iFile);
        wtriteShares(iFile);
        writeHotfixes(iFile);
        writeSoftware(iFile);

        return true;
    }

    private void writeSoftware(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        Iterator iter = iFile.getSoftware().iterator();
        Map<String, String> software;
        while(iter.hasNext()){
            software = (Map<String, String>) iter.next();
            sta = this.conn.prepareStatement(INSERT_SOFTWARE);
            sta.setString(1, lastId);
            sta.setString(2, software.get("Name"));
            sta.setString(3, software.get("Version"));
            sta.setString(4, software.get("Publisher"));
            sta.setString(5, software.get("Size"));
            sta.setString(6, software.get("Install date"));
            sta.executeUpdate();
        }
    }

    private void writeHotfixes(InfoFile iFile) throws SQLException {
        PreparedStatement sta;
        for (String hotxix : iFile.getSystem().getHotfixes()) {
            sta = this.conn.prepareStatement(INSERT_HOTFIX);
            sta.setString(1, lastId);
            sta.setString(2, hotxix);
            sta.executeUpdate();
        }
    }

    private void wtriteShares(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        for (Share share : iFile.getSystem().getShares()) {
            sta = this.conn.prepareStatement(INSERT_SHARE);
            sta.setString(1, lastId);
            sta.setString(2, share.getName());
            sta.setString(3, share.getCaption());
            sta.setString(4, share.getPath());
            sta.setString(5, share.getType());
            sta.executeUpdate();
        }
    }

    private void writeNetwoekAdapters(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        for (NetworkAdapter adapter : iFile.getSystem().getNetAdapters()) {
            sta = this.conn.prepareStatement(INSERT_NETWORK_ADAPTER);
            sta.setString(1, lastId);
            sta.setString(2, adapter.getAdapterType());
            sta.setString(3, adapter.getNetConnectionStatus());
            sta.setString(4, adapter.getManufacturer());
            sta.setString(5, adapter.getSpeed());
            sta.setString(6, adapter.getAdapterIPAddress());
            sta.setString(7, adapter.getAdapterMACAddress());
            sta.setString(8, adapter.getDHCPenabled());
            sta.setString(9, adapter.getDHCPserver());
            sta.setString(10, adapter.getDNSdomain());
            sta.setString(11, adapter.getWINSprimary());
            sta.setString(12, adapter.getWINSsecondary());
            sta.executeUpdate();
        }
    }

    private void writeMultimedias(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        for (Multimedia multimedia : iFile.getSystem().getMultimedias()) {
            sta = this.conn.prepareStatement(INSERT_MULTIMEDIA);
            sta.setString(1, lastId);
            sta.setString(2, multimedia.getName());
            sta.setString(3, multimedia.getManufacturer());
            sta.executeUpdate();
        }
    }

    private void writePrinters(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        for (Printer printer : iFile.getSystem().getPrinters()) {
            sta = this.conn.prepareStatement(INSERT_PRINTER);
            sta.setString(1, lastId);
            sta.setString(2, printer.getField1());
            sta.setString(3, printer.getField2());
            sta.setString(4, printer.getField3());
            sta.setString(5, printer.getField4());
            sta.executeUpdate();
        }
    }

    private void writeVideos(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        for (Video video : iFile.getSystem().getVideos()) {
            sta = this.conn.prepareStatement(INSERT_VIDEO);
            sta.setString(1, lastId);
            sta.setString(2, video.getName());
            sta.setString(3, video.getCurrentHorizontalResolution());
            sta.setString(4, video.getCurrentVerticalResolution());
            sta.setString(5, video.getCurrentBitsPerPixel());
            sta.setString(6, video.getCurrentRefreshRate());
            sta.executeUpdate();
        }
    }

    private void writeCdRoms(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        for (CdRom cdrom : iFile.getSystem().getCdroms()) {
            sta = this.conn.prepareStatement(INSERT_CDROM);
            sta.setString(1, lastId);
            sta.setString(2, cdrom.getName());
            sta.setString(3, cdrom.getMediaType());
            sta.setString(4, cdrom.getManufacturer());
            sta.setString(5, cdrom.getNetworkPath());
            sta.executeUpdate();
        }
    }

    private void writeLogicalDrives(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        for (LogicalDrive ld : iFile.getSystem().getLogicalDrives()) {
            sta = this.conn.prepareStatement(INSERT_LOGICAL_DRIVE);
            sta.setString(1, lastId);
            sta.setString(2, ld.getName());
            sta.setString(3, ld.getDescription());
            sta.setString(4, ld.getSize());
            sta.setString(5, ld.getFreeSpace());
            sta.setString(6, ld.getFileSystem());
            sta.setString(7, ld.getSerialNumber());
            sta.setString(8, ld.getNetworkPath());
            sta.executeUpdate();
        }
    }

    private void writeDisks(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        for (Disk disk : iFile.getSystem().getDisks()) {
            sta = this.conn.prepareStatement(INSERT_DISK);
            sta.setString(1, lastId);
            sta.setString(2, disk.getCaption());
            sta.setString(3, disk.getSize());
            sta.setString(4, disk.getManufacturer());
            sta.setString(5, disk.getInterfaceType());
            sta.setString(6, disk.getMediaType());
            sta.setString(7, disk.getBytesPerSector());
            sta.setString(8, disk.getHeads());
            sta.setString(9, disk.getCylinders());
            sta.setString(10, disk.getTracks());
            sta.setString(11, disk.getSectors());
            sta.executeUpdate();
        }
    }

    private void writeSlots(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        for (MemorySlot slot : iFile.getSystem().getMemorySlots()) {
            sta = this.conn.prepareStatement(INSERT_MEMORY_SLOT);
            sta.setString(1, lastId);
            sta.setString(2, slot.getCapacity());
            sta.setString(3, slot.getDeviceLocator());
            sta.setString(4, slot.getBankLabel());
            sta.setString(5, slot.getFormFactor());
            sta.setString(6, slot.getMemoryType());
            sta.setString(7, slot.getManufacturer());
            sta.setString(8, slot.getSpeed());
            sta.setString(9, slot.getMaxCapacity());
            sta.executeUpdate();
        }
    }

    private void writeProcessors(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        for (Processor processor : iFile.getSystem().getProcessor()) {
            sta = this.conn.prepareStatement(INSERT_PROCESSOR);
            sta.setString(1, lastId);
            sta.setString(2, processor.getName());
            sta.setString(3, processor.getManufacturer());
            sta.setString(4, processor.getMaxClockSpeed());
            sta.setString(5, processor.getArchitecture());
            sta.setString(6, processor.getL2cache());
            sta.setString(7, processor.getSocket());
            sta.executeUpdate();
        }
    }

    private void writeStartups(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        for (Startup startup : iFile.getSystem().getStartups()) {
            sta = this.conn.prepareStatement(INSERT_STARTUP);
            sta.setString(1, lastId);
            sta.setString(2, startup.getName());
            sta.setString(3, startup.getCommand());
            sta.setString(4, startup.getLocation());
            sta.setString(5, startup.getUser());
            sta.executeUpdate();
        }
    }

    private void writeLocalaccounts(InfoFile iFile) throws SQLException {
        PreparedStatement sta;


        for (LocalAccount la : iFile.getSystem().getLocalAccounts()) {
            sta = this.conn.prepareStatement(INSERT_LOCAL_ACCOUNT);
            sta.setString(1, lastId);
            sta.setString(2, la.getCaption());
            sta.setString(3, la.getName());
            sta.setString(4, la.getDomain());
            sta.setString(5, la.getDescription());
            sta.setString(6, la.getSID());
            sta.setString(7, la.getDisabled());
            sta.executeUpdate();
        }
    }

    private void writeMonitors(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        for (Monitor m : iFile.getSystem().getMonitors()) {
            sta = this.conn.prepareStatement(INSERT_MONITOR);
            sta.setString(1, lastId);
            sta.setString(2, m.getManufacturer());
            sta.setString(3, m.getName());
            sta.setString(4, m.getDiagonal());
            sta.setString(5, m.getSerialNumber());
            sta.setString(6, m.getMaxResolution());
            sta.setString(7, m.getDisplaySize());
            sta.setString(8, m.getManufactureDate());
            sta.executeUpdate();
        }
    }

    private void writeMotherboard(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        sta = this.conn.prepareStatement(INSERT_MOTHERBOARD);
        sta.setString(1, lastId);
        sta.setString(2, iFile.getSystem().getMotherboard().getManufacturer());
        sta.setString(3, iFile.getSystem().getMotherboard().getProduct());
        sta.setString(4, iFile.getSystem().getMotherboard().getVersion());
        sta.executeUpdate();
    }

    private void writeBIOS(InfoFile iFile) throws SQLException {
        PreparedStatement sta;

        sta = this.conn.prepareStatement(INSERT_BIOS);
        sta.setString(1, lastId);
        sta.setString(2, iFile.getSystem().getBIOS().getManufacturer());
        sta.setString(3, iFile.getSystem().getBIOS().getVersion());
        sta.setString(4, iFile.getSystem().getBIOS().getDate());
        sta.executeUpdate();
    }

    private void writeOperatingSystem(InfoFile iFile) throws SQLException {
        PreparedStatement sta;
        sta = this.conn.prepareStatement(INSERT_SYSTEM);
        sta.setString(1, lastId);
        sta.setString(2, iFile.getSystem().getOperatingSystem().getName());
        sta.setString(3, iFile.getSystem().getOperatingSystem().getVersion());
        sta.setString(4, iFile.getSystem().getOperatingSystem().getBuild());
        sta.setString(5, iFile.getSystem().getOperatingSystem().getInstallDate());
        sta.executeUpdate();
    }

    private String getLastId() throws SQLException {
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(SELECT_HOST_REPORT_ID);
        rs.next();
        return rs.getString(1);
    }

    private void writeHostReport(InfoFile iFile) throws SQLException {
        PreparedStatement sta = this.conn.prepareStatement(INSERT_HOST_REPORT);
        String id = iFile.getPath().split(Constants.HYPHEN)[0];
        sta.setString(1, iFile.getPath());
        sta.setString(2, iFile.getPersonalID());
        sta.setString(3, iFile.getPersonalName());
        sta.setInt(4, iFile.getPCOrder());
        sta.setString(5, iFile.getSystem().getHostName());
        sta.setString(6, iFile.getSystem().getIPAddress());
        sta.setString(7, iFile.getSystem().getUserName());
        sta.setString(8, iFile.getSystem().getDescription());
        sta.setString(9, iFile.getSystem().getWindowsProductID());
        sta.setString(10, iFile.getSystem().getWindowsProductKey());
        sta.setString(11, iFile.getSystem().getServicePack());
        sta.setString(12, iFile.getSystem().getIEVersion());
        sta.setString(13, iFile.getSystem().getModel());
        sta.setString(14, iFile.getSystem().getSystemType());
        sta.setString(15, iFile.getSystem().getSerialNumber());
        sta.setString(16, iFile.getSystem().getChasis());
        sta.setString(17, iFile.getSystem().getPhysicalMemory());
        sta.executeUpdate();
    }
}
