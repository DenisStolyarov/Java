package company.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        InetAddress currentAddress = null;
        InetAddress customAddress = null;
        InetAddress byteAddress = null;

        byte ip[] ={(byte)123, (byte)162, (byte)204, (byte)87};
        try {
            currentAddress = InetAddress.getLocalHost();
            customAddress = InetAddress.getByName("www.belstu.by");
            byteAddress = InetAddress.getByAddress("New", ip);

            URL url;
            String link = "http://www.tut.by";

            url = new URL(link);

            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))){
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                }
            }

            System.out.println(currentAddress.getHostAddress());
            System.out.println(customAddress.getHostAddress());
            System.out.println(byteAddress.getHostName() + ": connection -> " + byteAddress.isReachable(100));

        } catch (UnknownHostException e) {
            System.err.println("Address invalid: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
