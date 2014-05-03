package cz.velim.bchw;

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
            this.adapterType = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.netConnectionStatus = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.manufacturer = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.speed = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.adapterIPAddress = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.adapterMACAddress = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.DHCPenabled = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.DHCPserver = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.DNSdomain = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.WINSprimary = reader.readLine().split(DataSpec.COLON)[1].trim();
            this.WINSsecondary = reader.readLine().split(DataSpec.COLON)[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
