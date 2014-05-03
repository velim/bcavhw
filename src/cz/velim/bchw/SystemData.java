package cz.velim.bchw;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mvelek on 5/3/2014.
 */
public class SystemData {
    private String hostName;
    private String IPAddress;
    private String userName;
    private List<Monitor> monitors = new ArrayList<Monitor>();
    private List<LocalAccount> localAccounts = new ArrayList<LocalAccount>();
    private List<Startup> startups = new ArrayList<Startup>();
    private String description;
    private OperatingSystem operatingSystem;
    private String windowsProductID;
    private String windowsProductKey;
    private String IEVersion;
    private String model;
    private String systemType;
    private Bios BIOS;
    private String serialNumber;
    private Motherboard motherboard;
    private String chasis;
    private Processor processor;
    private String physicalMemory;
    private List<MemorySlot> memorySlots = new ArrayList<MemorySlot>();
    private List<Disk> disks = new ArrayList<Disk>();
    private List<LogicalDrive> logicalDrives = new ArrayList<LogicalDrive>();
    private CdRom cdrom;
    private List<Video> videos = new ArrayList<Video>();
    private List<Printer> printers = new ArrayList<Printer>();
    private List<Multimedia> multimedias = new ArrayList<Multimedia>();
    private List<NetworkAdapter> netAdapters = new ArrayList<NetworkAdapter>();
    private List<Share> shares = new ArrayList<Share>();
    private List<String> hotfixes = new ArrayList<String>();

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addMonitor(BufferedReader reader) {
        monitors.add(new Monitor(reader));
    }

    public List<Monitor> getMonitors() {
        return monitors;
    }

    public void addLocalAccount(BufferedReader reader) {
        localAccounts.add(new LocalAccount(reader));
    }

    public List<LocalAccount> getLocalAccounts() {
        return localAccounts;
    }

    public void addStartup(BufferedReader reader) {
        startups.add(new Startup(reader));
    }

    public List<Startup> getStartups() {
        return startups;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addOperatingSystem(BufferedReader reader) {
        operatingSystem = new OperatingSystem(reader);
    }

    public String getWindowsProductID() {
        return windowsProductID;
    }

    public void setWindowsProductID(String windowsProductID) {
        this.windowsProductID = windowsProductID;
    }

    public String getWindowsProductKey() {
        return windowsProductKey;
    }

    public void setWindowsProductKey(String windowsProductKey) {
        this.windowsProductKey = windowsProductKey;
    }

    public String getIEVersion() {
        return IEVersion;
    }

    public void setIEVersion(String IEVersion) {
        this.IEVersion = IEVersion;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public void setBIOS(BufferedReader reader) {
        this.BIOS = new Bios(reader);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(BufferedReader reader) {
        this.motherboard = new Motherboard(reader);
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public void setProcessor(BufferedReader reader) {
        this.processor = new Processor(reader);
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setPhysicalMemory(String physicalMemory) {
        this.physicalMemory = physicalMemory;
    }

    public String getPhysicalMemory() {
        return physicalMemory;
    }

    public void setMemorySlot(BufferedReader reader) {
        this.memorySlots.add(new MemorySlot(reader));
    }

    public void setDisk(BufferedReader reader) {
        this.disks.add(new Disk(reader));
    }

    public List<Disk> getDisks() {
        return disks;
    }

    public void setLogicalDrive(BufferedReader reader) {
        this.logicalDrives.add(new LogicalDrive(reader));
    }

    public void addCDROM(BufferedReader reader) {
        this.cdrom = new CdRom(reader);
    }

    public void addVideo(BufferedReader reader) {
        this.videos.add(new Video(reader));
    }

    public void addPrinter(BufferedReader reader) {
        this.printers.add(new Printer(reader));
    }

    public void addMultimedia(BufferedReader reader) {
        this.multimedias.add(new Multimedia(reader));
    }

    public void addNetworkAdapter(BufferedReader reader) {
        this.netAdapters.add(new NetworkAdapter(reader));
    }

    public void addShare(BufferedReader reader) {
        this.shares.add(new Share(reader));
    }

    public void addSysHotfix(String hotfix) {
        this.hotfixes.add(hotfix);
    }
}
