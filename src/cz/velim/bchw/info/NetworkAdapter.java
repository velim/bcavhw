package cz.velim.bchw.info;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mvelek on 5/3/2014.
 */
public class NetworkAdapter {
    private String adapterType;
    private String netConnectionStatus;
    private String manufacturer;
    private String speed;
    private String adapterIPAddress;
    private String adapterMACAddress;
    private String DHCPenabled;
    private String DHCPserver;
    private String DNSdomain;
    private String WINSprimary;
    private String WINSsecondary;

    public NetworkAdapter(BufferedReader reader) {
        try {
            this.adapterType = reader.readLine().split(Constants.COLON)[1].trim();
            this.netConnectionStatus = reader.readLine().split(Constants.COLON)[1].trim();
            this.manufacturer = reader.readLine().split(Constants.COLON)[1].trim();
            this.speed = reader.readLine().split(Constants.COLON)[1].trim();
            this.adapterIPAddress = reader.readLine().split(Constants.COLON)[1].trim();
            this.adapterMACAddress = parseMACAddress(reader.readLine());
            this.DHCPenabled = reader.readLine().split(Constants.COLON)[1].trim();
            this.DHCPserver = reader.readLine().split(Constants.COLON)[1].trim();
            this.DNSdomain = reader.readLine().split(Constants.COLON)[1].trim();
            this.WINSprimary = reader.readLine().split(Constants.COLON)[1].trim();
            this.WINSsecondary = reader.readLine().split(Constants.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseMACAddress(String s) {
        int pos = s.indexOf(Constants.COLON);
        return s.substring(pos+1);
    }

    public String getAdapterType() {
        return adapterType;
    }

    public String getNetConnectionStatus() {
        return netConnectionStatus;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getSpeed() {
        return speed;
    }

    public String getAdapterIPAddress() {
        return adapterIPAddress;
    }

    public String getAdapterMACAddress() {
        return adapterMACAddress;
    }

    public String getDHCPenabled() {
        return DHCPenabled;
    }

    public String getDHCPserver() {
        return DHCPserver;
    }

    public String getDNSdomain() {
        return DNSdomain;
    }

    public String getWINSprimary() {
        return WINSprimary;
    }

    public String getWINSsecondary() {
        return WINSsecondary;
    }
}
